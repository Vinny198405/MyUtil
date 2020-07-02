package _40_HW_EmployeesMapsStreams.items;

import _40_HW_EmployeesMapsStreams.api.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;

public class DisplayEmployeesCompany extends EmployeesItem {

    public DisplayEmployeesCompany(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Display of employees by company";
    }

    @Override
    public void perform() {
        String company = inputOutput.inputString("Enter company");
        inputOutput.displayLine(employees.getEmployeesCompany(company));
    }
}
