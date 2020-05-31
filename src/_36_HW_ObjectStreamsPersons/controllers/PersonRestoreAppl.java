package _36_HW_ObjectStreamsPersons.controllers;

import _36_HW_ObjectStreamsPersons.dto.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PersonRestoreAppl {
    private static Map<String, List<Employee>> company = new HashMap<>();
    private static int averagingSalary = 0;

    public static void main(String[] args) throws Exception {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("person.data"))) {
            @SuppressWarnings("unchecked")

            List<Person> person = (List<Person>) input.readObject();

            displayMostPopulatedCities(person); // Most populated cities

            getCompanyMap(person);
            displayCompanyAveragingSalary();
            displayDataOfEmployers(); // Data about all employees with salary greater than overall average salary
        }
    }

    private static void displayDataOfEmployers() {
        company.values().forEach(s -> {
            s.forEach(v -> {
                if (v.getSalary() > averagingSalary) System.out.println(v);
            });
        });
    }

    private static void displayCompanyAveragingSalary() {
        company.forEach((k, v) -> {
            v.stream().collect(Collectors.groupingBy(Employee::getCompany,
                    Collectors.summingInt(s -> s.getSalary() / v.size()))).forEach((v1, v2) -> {
                System.out.println("Company name: " + v1 + " averaging salary:" + v2);
                averagingSalary += v2;
            });
        });
        averagingSalary /= company.size();
    }

    private static void getCompanyMap(List<Person> person) {
        person.stream().filter(p -> p instanceof Employee)
                .forEach(p -> company.computeIfAbsent(((Employee) p)
                        .getCompany(), k -> new ArrayList<>()).add((Employee) p));
    }

    private static void displayMostPopulatedCities(List<Person> person) {
        List listOfMax = person.stream().collect(Collectors.groupingBy(p -> p.getAddress().getCity(),
                Collectors.counting())).entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())))
                .entrySet().stream().max(Map.Entry.comparingByKey()).get().getValue();

        System.out.println("Most populated cities: " + listOfMax);
    }
}
