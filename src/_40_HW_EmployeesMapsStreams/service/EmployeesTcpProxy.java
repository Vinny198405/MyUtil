package _40_HW_EmployeesMapsStreams.service;

import EmployeesMaps.Employee;
import EmployeesMaps.EmployeesReturnCodes;
import EmployeesMaps.EmployeesService;
import _38_CW_TcpJava.net.TcpClientJava;
import _40_HW_EmployeesMapsStreams.dto.CompanySalary;
import _40_HW_EmployeesMapsStreams.dto.RequestData;

import java.util.List;
import java.util.Map;

import static _40_HW_EmployeesMapsStreams.api.EmployeesApi.*;

public class EmployeesTcpProxy extends TcpClientJava implements EmployeesService {

    public EmployeesTcpProxy(String hostname, int port) {
        super(hostname, port);
    }

    @Override
    public EmployeesReturnCodes addEmployee(Employee empl) {
        return sendRequest(ADD_EMPLOYEE, empl);
    }

    @Override
    public EmployeesReturnCodes removeEmployee(long id) {
        return sendRequest(REMOVE_EMPLOYEE, id);
    }

    @Override
    public Employee getEmployee(long id) {
        return sendRequest(GET_EMPLOYEE, id);
    }

    @Override
    public Iterable<Employee> getEmployees() {
        return sendRequest(GET_EMPLOYEES, null);
    }

    @Override
    public Iterable<Employee> getEmployeesCompany(String company) {
        return sendRequest(GET_EMPLOYEES_COMPANY, company);
    }

    @Override
    public Iterable<Employee> getEmployeesAges(int ageFrom, int ageTo) {
        RequestData data = new RequestData(ageFrom, ageTo);
        return sendRequest(GET_EMPLOYEES_AGES, data);
    }

    @Override
    public Iterable<Employee> getEmployeesSalary(int salaryFrom, int salaryTo) {
        RequestData data = new RequestData(salaryFrom, salaryTo);
        return sendRequest(GET_EMPLOYEES_SALARY, data);
    }

    @Override
    public EmployeesReturnCodes updateCompany(long id, String newCompany) {
        RequestData data = new RequestData(id, newCompany);
        return sendRequest(UPDATE_COMPANY, data);

    }

    @Override
    public EmployeesReturnCodes updateSalary(long id, int newSalary) {
        RequestData data = new RequestData(id, newSalary);
        return sendRequest(UPDATE_SALARY, data);
    }

    @Override
    public Map<String, List<Employee>> getEmployeesGroupedBySalary(int interval) {
        return sendRequest(GET_EMPLOYEES_GROUPER_BY_SALARY, interval);
    }

    @Override
    public List<CompanySalary> getCompaniesAvgSalary() {
        return sendRequest(GET_COMPANIES_AVG_SALARY, null);
    }

    @Override
    public List<CompanySalary> getCompaniesGreaterAvgSalary() {
        return sendRequest(GET_COMPANIES_GREATER_AVG_SALARY, null);
    }
}
