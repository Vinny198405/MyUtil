package _40_HW_EmployeesMapsStreams.items;

import EmployeesMaps.EmployeesReturnCodes;
import EmployeesMaps.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;

public class RemoveEmployees extends EmployeesItem {

    public RemoveEmployees(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Remove Employee";
    }

    @Override
    public void perform() {
        long id = inputOutput.inputInteger("Enter id employee for remove");
        EmployeesReturnCodes res = employees.removeEmployee(id);
        inputOutput.displayLine(res);
    }
}

