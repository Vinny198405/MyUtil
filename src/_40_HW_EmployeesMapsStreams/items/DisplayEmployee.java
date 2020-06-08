package _40_HW_EmployeesMapsStreams.items;

import EmployeesMaps.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;

public class DisplayEmployee extends EmployeesItem {

    public DisplayEmployee(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Display employee by id";
    }

    @Override
    public void perform() {
        long id = inputOutput.inputInteger("Enter id employee");
        inputOutput.displayLine(employees.getEmployee(id));
    }
}
