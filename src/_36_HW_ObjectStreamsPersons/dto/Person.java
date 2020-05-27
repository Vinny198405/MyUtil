package _36_HW_ObjectStreamsPersons.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    int id;
    Address address;
    String name;
    LocalDate birthDate;

    public Person(){}

    public Person(int id, Address address, String name, LocalDate birthDate) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", address=" + address +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
