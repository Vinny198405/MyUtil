package _40_HW_EmployeesMapsStreams.api;

public interface EmployeesApi {
    String ADD_EMPLOYEE = "addEmployee";
    String REMOVE_EMPLOYEE = "removeEmployee";
    String GET_EMPLOYEE = "getEmployee";
    String GET_EMPLOYEES = "getEmployees";
    String GET_EMPLOYEES_AGES = "getEmployeesAges";
    String GET_EMPLOYEES_COMPANY  = "getEmployeesCompany";
    String GET_EMPLOYEES_SALARY  =  "getEmployeesSalary";
    String UPDATE_COMPANY  = "updateCompany";
    String UPDATE_SALARY  = "updateSalary";
    String GET_EMPLOYEES_GROUPER_BY_SALARY  = "getEmployeesGroupedBySalary";
    String GET_COMPANIES_AVG_SALARY  = "getCompaniesAvgSalary";
    String GET_COMPANIES_GREATER_AVG_SALARY = "getCompaniesGreaterAvgSalary";
}
