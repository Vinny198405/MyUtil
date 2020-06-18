package _44_HW_ThreadsRace.item;

import EmployeesMaps.EmployeesService;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;
import _39_HW_MenuItemsInputOutput.menu.Item;
import _44_HW_ThreadsRace.ThreadsRace;

public abstract class ThreadsRaceItem implements Item {
    ThreadsRace threadsRace;
    InputOutput inputOutput;

    public ThreadsRaceItem(ThreadsRace threadsRace, InputOutput inputOutput) {
        super();
        this.threadsRace = threadsRace;
        this.inputOutput = inputOutput;
    }
}
