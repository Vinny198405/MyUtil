package _39_HW_MenuItemsInputOutput.menu;

public class ExitItem implements Item {

    @Override
    public String displayName() {
        return "Exit";
    }

    @Override
    public void perform() {
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
