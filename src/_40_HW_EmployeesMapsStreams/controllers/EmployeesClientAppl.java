package _40_HW_EmployeesMapsStreams.controllers;

import _40_HW_EmployeesMapsStreams.api.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.*;
import _40_HW_EmployeesMapsStreams.items.*;
import _40_HW_EmployeesMapsStreams.service.EmployeesTcpProxy;

public class EmployeesClientAppl {
    private static final int PORT = 4000;
    private static final String HOST = "localhost";
    private static InputOutput inputOutput = new ConsoleInputOutput();
    //   private static EmployeesService employees = new EmployeesServiceMapsImpl();
    private static EmployeesService employees = new EmployeesTcpProxy(HOST, PORT);

    public static void main(String[] args) {
        Item[] items = {
                new AddEmployee(employees, inputOutput),
                new RandomGeneration(employees, inputOutput),
                new DisplayEmployee(employees, inputOutput),
                new DisplayAllEmployees(employees, inputOutput),
                new DisplayEmployeesSalary(employees, inputOutput),
                new DisplayEmployeesAges(employees, inputOutput),
                new DisplayEmployeesCompany(employees, inputOutput),
                new DisplayCompaniesAvgSalary(employees, inputOutput),
                new DisplayCompaniesGreaterAvgSalary(employees, inputOutput),
                new RemoveEmployees(employees, inputOutput),
                new UpdateCompany(employees, inputOutput),
                new UpdateSalary(employees, inputOutput),
                new ExitEmployeesItem(employees, inputOutput),
        };
        Menu menu = new Menu(items, inputOutput);
        menu.menuRun();
    }
}
