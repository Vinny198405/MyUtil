package _40_HW_EmployeesMapsStreams.items;

import EmployeesMaps.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;

public class DisplayEmployeesAges extends EmployeesItem {

    public DisplayEmployeesAges(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Display all employees with a given ages";
    }

    @Override
    public void perform() {
        int agesFrom = inputOutput.inputInteger("Enter ages from");
        int agesTo = inputOutput.inputInteger("Enter ages to", agesFrom, Integer.MAX_VALUE);
        inputOutput.displayLine(employees.getEmployeesAges(agesFrom, agesTo));
    }
}
