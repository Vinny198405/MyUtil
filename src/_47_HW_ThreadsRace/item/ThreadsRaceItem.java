package _47_HW_ThreadsRace.item;

import _39_HW_MenuItemsInputOutput.menu.*;
import _47_HW_ThreadsRace.ThreadsRace;

public abstract class ThreadsRaceItem implements Item {
    ThreadsRace threadsRace;
    InputOutput inputOutput;

    public ThreadsRaceItem(ThreadsRace threadsRace, InputOutput inputOutput) {
        super();
        this.threadsRace = threadsRace;
        this.inputOutput = inputOutput;
    }
}
