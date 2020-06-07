package _40_HW_EmployeesMapsStreams.items;

import EmployeesMaps.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;

public class DisplayEmployeesSalary extends EmployeesItem {

    public DisplayEmployeesSalary(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Display all employees with a given salary";
    }

    @Override
    public void perform() {
        int salaryFrom = inputOutput.inputInteger("Enter salary from");
        int salaryTo = inputOutput.inputInteger("Enter salary to", salaryFrom, Integer.MAX_VALUE);
        inputOutput.displayLine(employees.getEmployeesSalary(salaryFrom, salaryTo));
    }
}
