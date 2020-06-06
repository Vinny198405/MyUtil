package _39_HW_MenuItemsInputOutput;

import _39_HW_MenuItemsInputOutput.menu.InputOutput;
import _39_HW_MenuItemsInputOutput.menu.Item;

public class CalculatorItem implements Item {
    CalculatorItem(InputOutput inputOutput) {
        super();
        this.inputOutput = inputOutput;
    }

    private InputOutput inputOutput;

    @Override
    public String displayName() {
        return "Divide two numbers";
    }

    @Override
    public void perform() {
        Integer n1 = inputOutput.inputInteger("Enter number:", 1, 100);
        Integer n2 = inputOutput.inputInteger("Enter number:", 0, 100);
        inputOutput.displayLine(n1 / n2);

    }

}
