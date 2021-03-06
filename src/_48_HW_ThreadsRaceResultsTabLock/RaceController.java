package _48_HW_ThreadsRaceResultsTabLock;

import _39_HW_MenuItemsInputOutput.menu.*;

public class RaceController {
    private static InputOutput inputOutput = new ConsoleInputOutput();

    public static void main(String[] args) {
        Item[] items = {
                new StartRace(inputOutput),
                new ExitItem()
        };
        Menu menu = new Menu(items, inputOutput);
        menu.menuRun();
    }
}
