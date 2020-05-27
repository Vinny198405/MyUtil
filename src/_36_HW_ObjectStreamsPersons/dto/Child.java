package _36_HW_ObjectStreamsPersons.dto;

import java.time.LocalDate;

public class Child extends Person {
    private String garden; // name of kinder garden

    public Child(int id, Address address, String name, LocalDate birthDate, String garden) {
        super(id, address, name, birthDate);
        this.garden = garden;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", " + address +
                ", birthDate=" + birthDate +
                ", garden='" + garden + '\'' +
                '}';
    }
}
