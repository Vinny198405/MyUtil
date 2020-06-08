package _40_HW_EmployeesMapsStreams.items;

import EmployeesMaps.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;

public class UpdateSalary extends EmployeesItem {

    public UpdateSalary(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Update Salary";
    }

    @Override
    public void perform() {
        long id = inputOutput.inputInteger("Enter id employee from update salary");
        inputOutput.displayLine("employee salary: " + employees.getEmployee(id).getSalary());
        int newSalary = inputOutput.inputInteger("Enter new salary");
        inputOutput.displayLine(employees.updateSalary(id, newSalary));
    }
}
