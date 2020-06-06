package _39_HW_MenuItemsInputOutput;

import _39_HW_MenuItemsInputOutput.menu.InputOutput;
import _39_HW_MenuItemsInputOutput.menu.Item;

public class CheckingPhone implements Item {
    CheckingPhone(InputOutput inputOutput) {
        super();
        this.inputOutput = inputOutput;
    }

    private InputOutput inputOutput;

    @Override
    public String displayName() {
        return "Checking Phone";
    }

    @Override
    public void perform() {
        String phone = inputOutput.inputPhone("Enter phone:");
        inputOutput.displayLine(phone);

    }

}
