package _39_HW_MenuItemsInputOutput;

import _39_HW_MenuItemsInputOutput.menu.InputOutput;
import _39_HW_MenuItemsInputOutput.menu.Item;

public class CheckingIpV4 implements Item {
    CheckingIpV4(InputOutput inputOutput) {
        super();
        this.inputOutput = inputOutput;
    }

    private InputOutput inputOutput;

    @Override
    public String displayName() {
        return "Checking IpV4";
    }

    @Override
    public void perform() {
        String ipV4 = inputOutput.inputIpV4("Enter IpV4:");
        inputOutput.displayLine(ipV4);

    }

}
