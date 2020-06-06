package _39_HW_MenuItemsInputOutput;

import _39_HW_MenuItemsInputOutput.menu.*;

public class CalculatorAppl {
    private static InputOutput inputOutput = new ConsoleInputOutput();

    public static void main(String[] args) {
        Item[] items = {
                new CalculatorItem(inputOutput),
                new CalculatorItemAddition(inputOutput),
                new CheckingEmail(inputOutput),
                new CheckingPhone(inputOutput),
                new CheckingIpV4(inputOutput),
                new DaysCalculation(inputOutput),
                new ExitItem()
        };
        Menu menu = new Menu(items, inputOutput);
        menu.menuRun();
    }
}
