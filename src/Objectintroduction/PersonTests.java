package Objectintroduction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


class PersonTests {

    @Test
    void testPerson() {
        Person person = new Person();
        assertNotNull(person);
        assertNull(person.getName());
    }

    @Test
    void testPersonLongStringInt() {
        long id = 123;
        String name = "Moshe";
        int birthYear = 1980;
        Person person = new Person(id, name, birthYear);
        assertEquals(name, person.getName());
        assertEquals(birthYear, person.getBirthYear());
        assertEquals(id, person.getId());
    }

    @Test
    void testSetName() {
        String name = "Moshe";
        Person person = new Person();
        person.setName(name);
        assertEquals(name, person.getName());
    }

    @Test
    void testEqualedObjects() {
        Person person1 = new Person(123, "Moshe", 1980);
        Person person2 = new Person(123, "Moshe", 1980);
        assertEquals(person1, person2);
    }

    @Test
    void testSorting() {
        Person persons[] = {
                new Person(123, "Moshe", 1980),
                new Person(100, "Vova", 1970)};
        Arrays.sort(persons);
        assertEquals(100, persons[0].getId());
        assertEquals(123, persons[1].getId());
        Arrays.sort(persons, new PersonAgeComparator());

    }

}
