package _39_HW_MenuItemsInputOutput;

import _39_HW_MenuItemsInputOutput.menu.InputOutput;
import _39_HW_MenuItemsInputOutput.menu.Item;

public class CheckingEmail implements Item {
    CheckingEmail(InputOutput inputOutput) {
        super();
        this.inputOutput = inputOutput;
    }

    private InputOutput inputOutput;

    @Override
    public String displayName() {
        return "Checking Email";
    }

    @Override
    public void perform() {
        String email = inputOutput.inputEmail("Enter email:");
        inputOutput.displayLine(email);

    }

}
