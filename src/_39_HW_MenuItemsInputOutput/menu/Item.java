package _39_HW_MenuItemsInputOutput.menu;

public interface Item {
    String displayName();
    void perform();
    default boolean isExit() {
        return false;
    }
}
