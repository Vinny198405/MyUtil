package _48_HW_ThreadsRaceSyncWinnerAtomic;

import _39_HW_MenuItemsInputOutput.menu.*;

public class RaceController {
    static InputOutput inputOutput = new ConsoleInputOutput();

    public static void main(String[] args) {
        Item[] items = {
                new StartRace(inputOutput),
                new ExitItem()
        };
        Menu menu = new Menu(items, inputOutput);
        menu.menuRun();
    }
}
