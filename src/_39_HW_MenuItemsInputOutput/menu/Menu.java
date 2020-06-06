package _39_HW_MenuItemsInputOutput.menu;

public class Menu {

    private Item[] items;
    private InputOutput inputOutput;

    public Menu(Item[] items, InputOutput inputOutput) {
        this.items = items;
        this.inputOutput = inputOutput;
    }

    public void menuRun() {
        Item item = null;
        do {
            for (int i = 1; i <= items.length; i++) {
                inputOutput.displayLine(i + ". " + items[i - 1].displayName());
            }
            int nItem = inputOutput.inputInteger
                    ("Select item number", 1, items.length);
            item = items[nItem - 1];
            try {
                item.perform();
            } catch (Exception e) {
                inputOutput.displayLine(e.getMessage());
            }
        } while (!item.isExit());
    }
}
