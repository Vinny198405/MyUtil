package _40_HW_EmployeesMapsStreams.items;

import _40_HW_EmployeesMapsStreams.api.EmployeesService;
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
       // inputOutput.displayLine(employees.getCompaniesGreaterAvgSalary());
        employees.getCompaniesGreaterAvgSalary().forEach(inputOutput::displayLine);
    }
}
