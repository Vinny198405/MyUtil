package _47_HW_ThreadsRace;

import _39_HW_MenuItemsInputOutput.menu.*;
import _47_HW_ThreadsRace.item.*;

public class ThreadsRaceAppl {
private static InputOutput inputOutput = new ConsoleInputOutput();
    private static ThreadsRace threadsRace;

    public static void main(String[] args){
        Item[] items = {
                new ThreadsRaceStartItem(threadsRace, inputOutput),
                new ExitThreadRaceItem(threadsRace, inputOutput),
        };
        Menu menu = new Menu(items, inputOutput);
        menu.menuRun();
    }
}
