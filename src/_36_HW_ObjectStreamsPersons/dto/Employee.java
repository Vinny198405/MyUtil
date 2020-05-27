package _36_HW_ObjectStreamsPersons.dto;

import java.time.LocalDate;

public class Employee extends Person {
    private String company;
    private String title; // Possible values: QA tester, Development Programmer, Development Manager, QA Manager
    private int salary;

    public Employee(int id, Address address, String name, LocalDate birthDate, String company, String title, int salary) {
        super(id, address, name, birthDate);
        this.company = company;
        this.title = title;
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", " + address +
                ", birthDate=" + birthDate +
                ", company='" + company + '\'' +
                ", title='" + title + '\'' +
                ", salary=" + salary +
                '}';
    }
}
