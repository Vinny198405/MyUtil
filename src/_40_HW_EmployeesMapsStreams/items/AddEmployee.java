package _40_HW_EmployeesMapsStreams.items;

import EmployeesMaps.Employee;
import EmployeesMaps.EmployeesReturnCodes;
import EmployeesMaps.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;

import java.time.LocalDate;

public class AddEmployee extends EmployeesItem {

    public AddEmployee(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Add Employee";
    }

    @Override
    public void perform() {
        long id = inputOutput.inputInteger("Enter employee's id [100000-999999]", 100000, 999999);
        Employee empl = employees.getEmployee(id);
        if (empl != null) {
            inputOutput.displayLine("Employee already exiats");
            return;
        }
        int salary = inputOutput.inputInteger("Enter salary [5000-50000]", 5000, 50000);
        String company = inputOutput.inputString("Enter company");
        LocalDate birthDate = inputOutput.inputDate("Enter birth date YYYY-MM-DD");
        String name = inputOutput.inputString("Enter name");
        Employee employee = new Employee(id, salary, company, birthDate, name);
        EmployeesReturnCodes res = employees.addEmployee(employee);
        inputOutput.displayLine(res);
    }

}
