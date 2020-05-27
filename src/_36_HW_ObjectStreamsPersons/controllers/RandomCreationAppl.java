package _36_HW_ObjectStreamsPersons.controllers;

import _36_HW_ObjectStreamsPersons.dto.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RandomCreationAppl {
    private static final long N_PERSON = 100;
    private static final int RANDOM_PERSON = 80;
    private static int id = 0;
    private static final LocalDate date = LocalDate.now();
    private static final LocalDate MIN_YEAR_CHILD = date;
    private static final LocalDate MIN_YEAR_EMPLOYEE = date.minusYears(18);
    private static final LocalDate MAX_YEAR_CHILD = MIN_YEAR_EMPLOYEE;
    private static final LocalDate MAX_YEAR_EMPLOYEE = date.minusYears(67);

    public static void main(String[] args) throws FileNotFoundException, IOException {
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream("person.data"))) {
            List<Person> person = getRandomPersons();
            output.writeObject(person);
        }
    }

    private static List<Person> getRandomPersons() {
        return Stream.generate(RandomCreationAppl::getRandomPerson).distinct()
                .limit(N_PERSON).collect(Collectors.toList());
    }

    private static Person getRandomPerson() {
        return getChance() <= RANDOM_PERSON ? getEmployee() : getChild();
    }

    private static Person getChild() {
        return new Child(id++, getAddress(), getRandomString(5, 10), getBirthDay(MIN_YEAR_CHILD, MAX_YEAR_CHILD), getGarden());
    }

    private static String getGarden() {
        return "Garden" + getRandomNumber(1, 10);
    }

    private static LocalDate getBirthDay(LocalDate min, LocalDate max) {
        long startEpochDay = max.toEpochDay();
        long endEpochDay = min.toEpochDay();
        long randomDate = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDate);
    }

    private static Address getAddress() {
        return new Address(getCity(), getStreet(), getRandomNumber(1, 200), getRandomNumber(1, 50));
    }

    private static String getStreet() {
        Street[] allStreet = Street.values();
        int index = getRandomNumber(0, allStreet.length);
        return allStreet[index].toString();
    }

    private static String getCity() {
        City[] allCity = City.values();
        int index = getRandomNumber(0, allCity.length);
        return allCity[index].toString();
    }

    private static Person getEmployee() {
        return new Employee(id++, getAddress(), getRandomString(5, 10), getBirthDay(MIN_YEAR_EMPLOYEE, MAX_YEAR_EMPLOYEE), getCompany(), getTitle(), getRandomNumber(5000, 50000));
    }

    private static String getTitle() {
        Title[] allTitle = Title.values();
        int index = getRandomNumber(0, allTitle.length);
        return allTitle[index].toString();
    }

    private static String getCompany() {
        return "Company" + getRandomNumber(1, 10);
    }

    private static int getChance() {
        return getRandomNumber(0, 100);
    }

    private static int getRandomNumber(int min, int max) {

        return (int) (min + Math.random() * (max - min));
    }

    private static String getRandomString(int min, int max) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        return new Random().ints(leftLimit, rightLimit + 1)
                .limit(getRandomNumber(min, max))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
