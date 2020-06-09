package EmployeesMaps;

import _40_HW_EmployeesMapsStreams.dto.CompanySalary;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeesServiceMapsImpl implements EmployeesService {
    private HashMap<Long, Employee> employees = new HashMap<>();
    //key-company, value-list of employees working for that company
    private HashMap<String, List<Employee>> employeesCompany = new HashMap<>();
    /**************************************************/
    //key - age, value - list of employees with that age
    private TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
    /*****************************************************/
    //key - salary, value - list of employees with that salary
    private TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();

    /******************************************************/

    @Override
    public EmployeesReturnCodes addEmployee(Employee empl) {
        Employee res = employees.putIfAbsent(empl.getId(), empl);
        if (res != null) {
            return EmployeesReturnCodes.EMPLOYEE_ALREADY_EXISTS;
        }

        addToEmployees(empl, empl.getCompany(), employeesCompany);
        addToEmployees(empl, getAge(empl.getBirthYear()), employeesAge);
        addToEmployees(empl, empl.getSalary(), employeesSalary);
        return EmployeesReturnCodes.OK;
    }

    private <T> void addToEmployees(Employee empl, T key, Map<T, List<Employee>> employees) {
        List<Employee> list = employees.getOrDefault(key, new ArrayList<>());
        list.add(empl);
        employees.putIfAbsent(key, list);
    }

    @Override
    public EmployeesReturnCodes removeEmployee(long id) {
        Employee employee = employees.remove(id);
        if (employee == null) {
            return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
        }
        removeFromEmployees(employee, employee.getCompany(), employeesCompany);
        removeFromEmployees(employee, getAge(employee.getBirthYear()), employeesAge);
        removeFromEmployees(employee, employee.getSalary(), employeesSalary);
        return EmployeesReturnCodes.OK;
    }

    private <T> void removeFromEmployees(Employee employee, T key, Map<T, List<Employee>> empl) {
        List<Employee> list = empl.get(key);
        if (list.size() == 1) {
            empl.remove(key);
        } else list.remove(employee);
    }

    @Override
    public Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo) {
        return toListEmployees(employeesAge, ageFrom, ageTo);
    }

    private Iterable<Employee> toListEmployees(TreeMap<Integer,
            List<Employee>> employeesCollection, int from, int to) {
        return employeesCollection.subMap(from, true, to,
                true).values().stream().flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo) {
        return toListEmployees(employeesSalary, salaryFrom, salaryTo);
    }

    @Override
    public EmployeesReturnCodes updateCompany(long id, String newCompany) {
        Employee employee = getEmployee(id);
        if (employee == null) return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
        if (employee.getCompany().equals(newCompany)) return EmployeesReturnCodes.SAME_COMPANY;

        removeEmployee(id);
        return addEmployee(replaceData(employee, newCompany, employee.getSalary()));
    }

    @Override
    public EmployeesReturnCodes updateSalary(long id, int newSalary) {
        Employee employee = getEmployee(id);
        if (employee == null) return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
        if (employee.getSalary() == newSalary) return EmployeesReturnCodes.SAME_SALARY;

        removeEmployee(id);
        return addEmployee(replaceData(employee, employee.getCompany(), newSalary));
    }

    private Employee replaceData(Employee oldEmployee, String newCompany, int newSalary) {
        return new Employee(oldEmployee.getId(), newSalary, newCompany,
                oldEmployee.getBirthYear(), oldEmployee.getName());
    }

    @Override
    public Employee getEmployee(long id) {
        return employees.get(id);
    }

    @Override
    public Iterable<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public Iterable<Employee> getEmployeesCompany(String company) {
        return employeesCompany.getOrDefault(company, new ArrayList<>());
    }

    private int getAge(LocalDate birthDate) {
        return (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

    @Override
    public Map<String, List<Employee>> getEmployeesGroupedBySalary(int interval) {
        return employees.values().stream().
                collect(Collectors.groupingBy(v -> {
                    int minIntervalValue = v.getSalary() / interval * interval;
                    return String.format("%d - %d", minIntervalValue, minIntervalValue + interval - 1);
                }, Collectors.toList()));
    }

    @Override
    public List<CompanySalary> getCompaniesAvgSalary() {
        return getStreamCompanySalary().collect(Collectors.toList());
    }

    @Override
    public List<CompanySalary> getCompaniesGreaterAvgSalary() {
        double avgSalary = getAvgSalary();
        return getStreamCompanySalary().filter(cs -> cs.getAvgSalary() > avgSalary).collect(Collectors.toList());
    }

    private Double getAvgSalary() {
        return employees.values().stream().collect(Collectors.averagingInt(Employee::getSalary));
    }

    private Stream<CompanySalary> getStreamCompanySalary() {
        Map<String, Double> companiesSalary = employees.values().stream()
                .collect(Collectors.groupingBy(Employee::getCompany, Collectors.averagingInt(Employee::getSalary)));
        return companiesSalary.entrySet().stream()
                .map(e -> new CompanySalary(e.getKey(), e.getValue()));
    }

}
