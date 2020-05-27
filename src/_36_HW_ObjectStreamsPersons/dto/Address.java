package _36_HW_ObjectStreamsPersons.dto;

import java.io.Serializable;

public class Address implements Serializable {
    private String city;
    private String street;
    private int building;
    private int apart;

    public Address(String city, String street, int building, int apart) {
        this.city = city;
        this.street = street;
        this.building = building;
        this.apart = apart;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getBuilding() {
        return building;
    }

    public int getApart() {
        return apart;
    }

    @Override
    public String toString() {
        return "Address[" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building=" + building +
                ", apart=" + apart +
                ']';
    }
}
