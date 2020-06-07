package _40_HW_EmployeesMapsStreams.service;

import EmployeesMaps.Employee;
import EmployeesMaps.EmployeesReturnCodes;
import EmployeesMaps.EmployeesService;
import _38_CW_TcpJava.net.TcpClientJava;
import _40_HW_EmployeesMapsStreams.dto.CompanySalary;

import java.util.List;
import java.util.Map;

import static _40_HW_EmployeesMapsStreams.api.EmployeesApi.ADDEMPL;

public class EmployeesTcpProxy extends TcpClientJava  implements EmployeesService {

    public EmployeesTcpProxy(String hostname, int port) {
        super(hostname, port);
    }

    @Override
    public EmployeesReturnCodes addEmployee(Employee empl) {
        return sendRequest(ADDEMPL, empl);
    }

    @Override
    public EmployeesReturnCodes removeEmployee(long id) {
        return null;
    }

    @Override
    public Employee getEmployee(long id) {
        return null;
    }

    @Override
    public Iterable<Employee> getEmployees() {
        return null;
    }

    @Override
    public Iterable<Employee> getEmployeesCompany(String company) {
        return null;
    }

    @Override
    public Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo) {
        return null;
    }

    @Override
    public Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo) {
        return null;
    }

    @Override
    public EmployeesReturnCodes updateCompany(long id, String newCompany) {
        return null;
    }

    @Override
    public EmployeesReturnCodes updateSalary(long id, int newSalary) {
        return null;
    }

    @Override
    public Map<String, List<Employee>> getEmployeesGroupedBySalary(int interval) {
        return null;
    }

    @Override
    public List<CompanySalary> getCompaniesAvgSalary() {
        return null;
    }

    @Override
    public List<CompanySalary> getCompaniesGreaterAvgSalary() {
        return null;
    }
}
