package _40_HW_EmployeesMapsStreams.api;

import _40_HW_EmployeesMapsStreams.dto.Employee;
import _40_HW_EmployeesMapsStreams.dto.CompanySalary;
import _40_HW_EmployeesMapsStreams.dto.EmployeesReturnCodes;

import java.util.List;
import java.util.Map;

public interface EmployeesService {
    EmployeesReturnCodes addEmployee(Employee empl);
    EmployeesReturnCodes removeEmployee(long id);
    Employee getEmployee(long id);
    Iterable<Employee> getEmployees();
    Iterable<Employee> getEmployeesCompany(String company);
    Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo);
    Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo);
    EmployeesReturnCodes updateCompany(long id, String newCompany);
    EmployeesReturnCodes updateSalary(long id, int newSalary);
    Map<String, List<Employee>> getEmployeesGroupedBySalary(int interval);
    /*key - string containing interval, for example,  2000 - 3000 (interval=1000)
     * value - list of employees with salary in the key-interval (maximal interval value is exclusive )*/
    List<CompanySalary> getCompaniesAvgSalary();
    List<CompanySalary> getCompaniesGreaterAvgSalary();

}
