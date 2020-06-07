package _40_HW_EmployeesMapsStreams.items;

import EmployeesMaps.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;

public class UpdateCompany extends EmployeesItem {

    public UpdateCompany(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Update Company";
    }

    @Override
    public void perform() {
        long id = inputOutput.inputInteger("Enter id employee from update company");
        inputOutput.displayLine("employee company: " + employees.getEmployee(id).getCompany());
        String newCompany = inputOutput.inputString("Enter new company");
        inputOutput.displayLine(employees.updateCompany(id, newCompany));
    }
}
