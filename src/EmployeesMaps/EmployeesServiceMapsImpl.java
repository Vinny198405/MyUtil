package EmployeesMaps;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
        addEmployeeSalary(empl);
        addEmployeeAge(empl);
        addEmployeeCompany(empl);
        return EmployeesReturnCodes.OK;
    }

    private void addEmployeeCompany(Employee empl) {
        String company = empl.getCompany();
        List<Employee> listEmployeesCompany =
                employeesCompany.getOrDefault(company, new ArrayList<>());
        listEmployeesCompany.add(empl);
        employeesCompany.putIfAbsent(company, listEmployeesCompany);
    }

    private void addEmployeeAge(Employee empl) {
        int age = getAge(empl.getBirthYear());
        List<Employee> listEmployeesAge =
                employeesAge.getOrDefault(age, new ArrayList<>());
        listEmployeesAge.add(empl);
        employeesAge.putIfAbsent(age, listEmployeesAge);
    }

    private void addEmployeeSalary(Employee empl) {
        Integer salary = empl.getSalary();
        List<Employee> listEmployeesSalary =
                employeesSalary.getOrDefault(salary, new ArrayList<>());
        listEmployeesSalary.add(empl);
        employeesSalary.putIfAbsent(salary, listEmployeesSalary);
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
        Collection<List<Employee>> listCollection =
                employeesAge.subMap(ageFrom,true, ageTo,true).values();
        return toListEmployees(listCollection);
    }

    private Iterable<Employee> toListEmployees(Collection<List<Employee>> listCollection) {
        List<Employee> res = new ArrayList<>();
        for (List<Employee> list:listCollection){
            res.addAll(list);
        }
        return res;
    }

    @Override
    public Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo) {
        Collection<List<Employee>> listCollection =
                employeesSalary.subMap(salaryFrom,true, salaryTo,true).values();
        return toListEmployees(listCollection);
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
        return employees.values();
    }

    @Override
    public Iterable<Employee> getEmployeesCompany(String company) {
        return employeesCompany.getOrDefault(company, new ArrayList<>());
    }

    private int getAge(LocalDate birthDate) {
        return (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

}
