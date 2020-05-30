package _36_HW_ObjectStreamsPersons.controllers;

import _36_HW_ObjectStreamsPersons.dto.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PersonRestoreAppl {
    private static Map<String, Long> city;
    private static Map<String, List<Employee>> company = new HashMap<>();
    private static Map<String, Integer> mapSalary = new HashMap<>();

    public static void main(String[] args) throws Exception {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("person.data"))) {
            @SuppressWarnings("unchecked")

            List<Person> person = (List<Person>) input.readObject();

            getCityMap(person);
            displayMostPopulatedCities(city); // Most populated cities

            getCompanyMap(person);
            getMapSalary();
            displayCompanyAveragingSalary(); // Company names and averaging salary for each company
            displayDataOfEmployers(); // Data about all employees with salary greater than overall average salary
        }
    }

    private static void displayDataOfEmployers() {
        int averagingSalary = getAveragingSalary();
        company.values().forEach(s -> {
            s.forEach(v -> {
                if (v.getSalary() > averagingSalary) System.out.println(v);
            });
        });
    }

    private static int getAveragingSalary() {
        int count = 0;
        for (int l : mapSalary.values()) {
            count += l;
        }
        return count / mapSalary.size();
    }

    private static void displayCompanyAveragingSalary() {
        mapSalary.forEach((k, v) -> System.out.println("Company name: " + k + " averaging salary:" + v));
    }

    private static void getMapSalary() {
        company.forEach((k, v) -> {
            mapSalary.putAll(v.stream().collect(Collectors.groupingBy(Employee::getCompany,
                    Collectors.summingInt(s -> s.getSalary() / v.size()))));
        });
    }

    private static void getCompanyMap(List<Person> person) {
        person.stream().filter(p -> p instanceof Employee)
                .forEach(p -> company.computeIfAbsent(((Employee) p)
                        .getCompany(), k -> new ArrayList<>()).add((Employee) p));
    }

    private static void getCityMap(List<Person> person) {
        city = person.stream().collect(Collectors.groupingBy(p -> p.getAddress().getCity(),
                Collectors.counting()));
    }

    private static void displayMostPopulatedCities(Map<String, Long> city) {
        Long maxValueInMap = (Collections.max(city.values()));
        List listOfMax = city.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxValueInMap))
                .map(Map.Entry::getKey).collect(Collectors.toList());
        System.out.println("Most populated cities: " + listOfMax);
    }
}
