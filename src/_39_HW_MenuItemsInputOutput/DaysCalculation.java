package _39_HW_MenuItemsInputOutput;

import _39_HW_MenuItemsInputOutput.menu.InputOutput;
import _39_HW_MenuItemsInputOutput.menu.Item;

import java.time.LocalDate;
import java.time.Period;

public class DaysCalculation implements Item {
    DaysCalculation(InputOutput inputOutput) {
        super();
        this.inputOutput = inputOutput;
    }

    private InputOutput inputOutput;

    @Override
    public String displayName() {
        return "How many days have passed";
    }

    @Override
    public void perform() {
        LocalDate start = inputOutput.inputDate("Enter Date in format (yyyy-MM-dd):");
        LocalDate end = LocalDate.now();
        Period period = Period.between(start, end);
        String date = "passed: " + period.getYears() + " years " + period.getMonths()
                + " month " + period.getDays() + " days";
        inputOutput.displayLine(date);

    }
}
