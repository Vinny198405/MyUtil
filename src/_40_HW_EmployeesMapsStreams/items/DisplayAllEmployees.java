package _40_HW_EmployeesMapsStreams.items;

import _40_HW_EmployeesMapsStreams.api.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;

public class DisplayAllEmployees extends EmployeesItem {

    public DisplayAllEmployees(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Display all of employees";
    }

    @Override
    public void perform() {
        inputOutput.displayLine(employees.getEmployees());
    }
}
