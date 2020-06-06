package _39_HW_MenuItemsInputOutput;

import _39_HW_MenuItemsInputOutput.menu.InputOutput;
import _39_HW_MenuItemsInputOutput.menu.Item;

public class CalculatorItemAddition implements Item {
    CalculatorItemAddition(InputOutput inputOutput) {
        super();
        this.inputOutput = inputOutput;
    }

    private InputOutput inputOutput;

    @Override
    public String displayName() {
        return "Sum of numbers";
    }

    @Override
    public void perform() {
        Integer n1 = inputOutput.inputInteger("Enter number:");
        Integer n2 = inputOutput.inputInteger("Enter number:");
        inputOutput.displayLine(n1 + n2);

    }

}
