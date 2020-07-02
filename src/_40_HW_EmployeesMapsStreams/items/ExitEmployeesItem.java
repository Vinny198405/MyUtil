package _40_HW_EmployeesMapsStreams.items;


import _39_HW_MenuItemsInputOutput.menu.InputOutput;
import _40_HW_EmployeesMapsStreams.api.EmployeesService;

import java.io.Closeable;
import java.io.IOException;

public class ExitEmployeesItem extends EmployeesItem {

    public ExitEmployeesItem(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Exit";
    }

    @Override
    public void perform() {
        if (employees instanceof Closeable) {
            try {
                ((Closeable) employees).close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
