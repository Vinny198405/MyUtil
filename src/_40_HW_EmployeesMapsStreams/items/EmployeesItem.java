package _40_HW_EmployeesMapsStreams.items;

import _40_HW_EmployeesMapsStreams.api.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;
import _39_HW_MenuItemsInputOutput.menu.Item;

public abstract class EmployeesItem implements Item {

    EmployeesService employees;
    InputOutput inputOutput;

    public EmployeesItem(EmployeesService employees, InputOutput inputOutput) {
        super();
        this.employees = employees;
        this.inputOutput = inputOutput;
    }



}
