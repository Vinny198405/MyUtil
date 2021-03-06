package _40_HW_EmployeesMapsStreams.items;

import _40_HW_EmployeesMapsStreams.api.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;

public class DisplayCompaniesAvgSalary extends EmployeesItem {

    public DisplayCompaniesAvgSalary(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Display CompaniesAvgSalary";
    }

    @Override
    public void perform() {
      //  inputOutput.displayLine(employees.getCompaniesAvgSalary());
        employees.getCompaniesAvgSalary().forEach(inputOutput::displayLine);
    }
}
