package _40_HW_EmployeesMapsStreams.service;

import _40_HW_EmployeesMapsStreams.dto.Employee;
import _40_HW_EmployeesMapsStreams.dto.EmployeesReturnCodes;
import _40_HW_EmployeesMapsStreams.api.EmployeesService;
import _40_HW_EmployeesMapsStreams.dto.CompanySalary;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeesServiceMapsImpl implements EmployeesService {
    static private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    private HashMap<Long, Employee> employees = new HashMap<>();
    //key-company, value-list of employees working for that company
    private HashMap<String, List<Employee>> employeesCompany = new HashMap<>();
    /**************************************************/
    //key - age, value - list of employees with that age
    private TreeMap<Integer, List<Employee>> employeesAge = new TreeMap<>();
    /*****************************************************/
    //key - salary, value - list of employees with that salary
    private TreeMap<Integer, List<Employee>> employeesSalary = new TreeMap<>();

    @Override
    public EmployeesReturnCodes addEmployee(Employee empl) {
        try {
            writeLock.lock();
            Employee res = employees.putIfAbsent(empl.getId(), empl);
            if (res != null) {
                return EmployeesReturnCodes.EMPLOYEE_ALREADY_EXISTS;
            }

            addToEmployees(empl, empl.getCompany(), employeesCompany);
            addToEmployees(empl, getAge(empl.getBirthYear()), employeesAge);
            addToEmployees(empl, empl.getSalary(), employeesSalary);
            return EmployeesReturnCodes.OK;
        } finally {
            writeLock.unlock();
        }
    }

    private <T> void addToEmployees(Employee empl, T key, Map<T, List<Employee>> employees) {
            List<Employee> list = employees.getOrDefault(key, new ArrayList<>());
            list.add(empl);
            employees.putIfAbsent(key, list);
    }

    @Override
    public EmployeesReturnCodes removeEmployee(long id) {
        try {
            writeLock.lock();
            Employee employee = employees.remove(id);
            if (employee == null) {
                return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
            }
            removeFromEmployees(employee, employee.getCompany(), employeesCompany);
            removeFromEmployees(employee, getAge(employee.getBirthYear()), employeesAge);
            removeFromEmployees(employee, employee.getSalary(), employeesSalary);
            return EmployeesReturnCodes.OK;
        } finally {
            writeLock.unlock();
        }
    }

    private <T> void removeFromEmployees(Employee employee, T key, Map<T, List<Employee>> empl) {
            List<Employee> list = empl.get(key);
            if (list.size() == 1) {
                empl.remove(key);
            } else list.remove(employee);
    }

    @Override
    public Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo) {
        try {
            readLock.lock();
            return toListEmployees(employeesAge, ageFrom, ageTo);
        } finally {
            readLock.unlock();
        }
    }

    private Iterable<Employee> toListEmployees(TreeMap<Integer,
            List<Employee>> employeesCollection, int from, int to) {
        return employeesCollection.subMap(from, true, to,
                true).values().stream().flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo) {
        try {
            readLock.lock();
            return toListEmployees(employeesSalary, salaryFrom, salaryTo);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public EmployeesReturnCodes updateCompany(long id, String newCompany) {
        try {
            writeLock.lock();
            Employee employee = getEmployee(id);
            if (employee == null) return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
            if (employee.getCompany().equals(newCompany)) return EmployeesReturnCodes.SAME_COMPANY;

            removeEmployee(id);
            return addEmployee(replaceData(employee, newCompany, employee.getSalary()));
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public EmployeesReturnCodes updateSalary(long id, int newSalary) {
        try {
            writeLock.lock();
            Employee employee = getEmployee(id);
            if (employee == null) return EmployeesReturnCodes.EMPLOYEE_NOT_FOUND;
            if (employee.getSalary() == newSalary) return EmployeesReturnCodes.SAME_SALARY;

            removeEmployee(id);
            return addEmployee(replaceData(employee, employee.getCompany(), newSalary));
        } finally {
            writeLock.unlock();
        }
    }

    private Employee replaceData(Employee oldEmployee, String newCompany, int newSalary) {
        return new Employee(oldEmployee.getId(), newSalary, newCompany,
                oldEmployee.getBirthYear(), oldEmployee.getName());
    }

    @Override
    public Employee getEmployee(long id) {
        try {
            readLock.lock();
            return employees.get(id);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Iterable<Employee> getEmployees() {
        try {
            readLock.lock();
            return new ArrayList<>(employees.values());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Iterable<Employee> getEmployeesCompany(String company) {
        try {
            readLock.lock();
            return employeesCompany.getOrDefault(company, new ArrayList<>());
        } finally {
            readLock.unlock();
        }
    }

    private int getAge(LocalDate birthDate) {
        return (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
    }

    @Override
    public Map<String, List<Employee>> getEmployeesGroupedBySalary(int interval) {
        try {
            readLock.lock();
            return employees.values().stream().
                    collect(Collectors.groupingBy(v -> {
                        int minIntervalValue = v.getSalary() / interval * interval;
                        return String.format("%d - %d", minIntervalValue, minIntervalValue + interval - 1);
                    }, Collectors.toList()));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<CompanySalary> getCompaniesAvgSalary() {
        try {
            readLock.lock();
            return getStreamCompanySalary().collect(Collectors.toList());
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<CompanySalary> getCompaniesGreaterAvgSalary() {
        try {
            readLock.lock();
            double avgSalary = getAvgSalary();
            return getStreamCompanySalary().filter(cs -> cs.getAvgSalary() > avgSalary).collect(Collectors.toList());
        } finally {
            readLock.unlock();
        }
    }

    private Double getAvgSalary() {
        try {
            readLock.lock();
            return employees.values().stream().collect(Collectors.averagingInt(Employee::getSalary));
        } finally {
            readLock.unlock();
        }
    }

    private Stream<CompanySalary> getStreamCompanySalary() {
        Map<String, Double> companiesSalary = employees.values().stream()
                .collect(Collectors.groupingBy(Employee::getCompany, Collectors.averagingInt(Employee::getSalary)));
        return companiesSalary.entrySet().stream()
                .map(e -> new CompanySalary(e.getKey(), e.getValue()));
    }

}
