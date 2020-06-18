package _44_HW_ThreadsRace.item;

import _39_HW_MenuItemsInputOutput.menu.InputOutput;
import _44_HW_ThreadsRace.ThreadsRace;

import java.io.Closeable;
import java.io.IOException;

public class ExitThreadRaceItem extends ThreadsRaceItem {

    public ExitThreadRaceItem(ThreadsRace threadsRace, InputOutput inputOutput) {
        super(threadsRace, inputOutput);
    }

    @Override
    public String displayName() {
        return "Exit";
    }

    @Override
    public void perform() {
        if (threadsRace instanceof Closeable) {
            try {
                ((Closeable) threadsRace).close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
