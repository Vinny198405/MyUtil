package _40_HW_EmployeesMapsStreams.items;

import EmployeesMaps.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;

public class DisplayCompaniesGreaterAvgSalary extends EmployeesItem {

    public DisplayCompaniesGreaterAvgSalary(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Display CompaniesGreaterAvgSalary";
    }

    @Override
    public void perform() {
        inputOutput.displayLine(employees.getCompaniesGreaterAvgSalary());
    }
}
