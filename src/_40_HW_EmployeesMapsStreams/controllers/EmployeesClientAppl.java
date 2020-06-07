package _40_HW_EmployeesMapsStreams.controllers;

import EmployeesMaps.*;
import _39_HW_MenuItemsInputOutput.menu.*;
import _40_HW_EmployeesMapsStreams.items.*;

public class EmployeesClientAppl {
    private static InputOutput inputOutput = new ConsoleInputOutput();
    private static EmployeesService employees = new EmployeesServiceMapsImpl();

    public static void main(String[] args) {
        Item[] items = {
                new AddEmployee(employees, inputOutput),
                new RandomGeneration(employees,inputOutput),
                new RemoveEmployees(employees,inputOutput),
                new DisplayEmployeesSalary(employees, inputOutput),
                new DisplayEmployeesAges(employees, inputOutput),
                new UpdateCompany(employees, inputOutput),
                new UpdateSalary(employees, inputOutput),
                new DisplayAllEmployees(employees, inputOutput),
                new DisplayEmployeesCompany(employees, inputOutput),
                new DisplayCompaniesAvgSalary(employees, inputOutput),
                new DisplayCompaniesGreaterAvgSalary(employees, inputOutput),
                new ExitEmployeesItem(employees, inputOutput),
        };
        Menu menu = new Menu(items, inputOutput);
        menu.menuRun();
    }
}
