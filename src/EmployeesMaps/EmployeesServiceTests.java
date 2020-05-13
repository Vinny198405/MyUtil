package EmployeesMaps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesServiceTests {
    private static final int SALARY1 = 1000;
    private static final String COMPANY1 = "company1";
    private static final LocalDate BIRTH_DATE1 =
            LocalDate.ofYearDay(1980, 300);
    private static final int SALARY2 = 2000;
    private static final LocalDate BIRTH_DATE2 =
            LocalDate.ofYearDay(1990, 1);
    private static final int SALARY3 = 3000;
    private static final String COMPANY2 = "company2";
    private static final int SALARY4 = 4000;
    private static final LocalDate BIRTH_DATE3 = LocalDate.ofYearDay(1980, 1);
    private static final LocalDate BIRTH_DATE4 = LocalDate.ofYearDay(1970, 1);
    private static final long ID1 = 1;
    private static final long ID2 = 2;
    private static final long ID3 = 3;
    private static final long ID4 = 4;
    Employee empl1 = new Employee(ID1, SALARY1, COMPANY1,
            BIRTH_DATE1, "name");
    Employee empl2 = new Employee(ID2, SALARY2, COMPANY1,
            BIRTH_DATE2, "name");
    Employee empl3 = new Employee(ID3, SALARY3, COMPANY2,
            BIRTH_DATE3, "name");
    Employee empl4 = new Employee(ID4, SALARY4, COMPANY2,
            BIRTH_DATE4, "name");
    Employee newEmployee = new Employee(123777, SALARY4, COMPANY2,
            BIRTH_DATE4, "name");
    Employee employees[] = {empl1, empl2, empl3, empl4};
    EmployeesService employeesService ;
    @BeforeEach
    void setUp() throws Exception {
        employeesService = new EmployeesServiceMapsImpl();
        for (Employee empl: employees) {
            employeesService.addEmployee(empl);
        }
    }

    @Test
    void testAddEmployee() {
        assertEquals(EmployeesReturnCodes.EMPLOYEE_ALREADY_EXISTS,
                employeesService.addEmployee(empl1));
        assertEquals(EmployeesReturnCodes.OK,
                employeesService.addEmployee(newEmployee));
    }

    @Test
    void testRemoveEmployee() {
        assertEquals
                (EmployeesReturnCodes.EMPLOYEE_NOT_FOUND,
                        employeesService.removeEmployee(77777));
        Employee expected[] = {empl2, empl3, empl4};
        assertEquals
                (EmployeesReturnCodes.OK,
                        employeesService.removeEmployee(ID1));
        testEmployees(expected, employeesService.getEmployees());

    }

    private void testEmployees(Employee[] expected,
                               Iterable<Employee> employeesIter) {
        Employee[] actual = new Employee[expected.length];
        int ind = 0;
        for(Employee empl: employeesIter) {
            actual[ind++] = empl;
        }
        Arrays.sort(actual, new ComparatorEmployees());
        assertArrayEquals(expected, actual);

    }

    @Test
    void testGetEmployee() {
        assertEquals(empl1, employeesService.getEmployee(ID1));
        assertNull(employeesService.getEmployee(777777));
    }



    @Test
    void testGetEmployeesCompany() {
        Employee expected[] = {empl1, empl2};
        Iterable<Employee> employeesEmpty =
                employeesService.getEmployeesCompany("company");
        assertFalse(employeesEmpty.iterator().hasNext());
        Iterable<Employee> employeesCompany1 =
                employeesService.getEmployeesCompany(COMPANY1);
        testEmployees(expected, employeesCompany1);


    }

    @Test
    void testGetEmployeesAges() {
        Employee expected[] = {empl3, empl4};
        Iterable<Employee> employeesEmpty =
                employeesService.getEmployeesAges(100, 120);
        assertFalse(employeesEmpty.iterator().hasNext());
        Iterable<Employee> employees40Plus =
                employeesService.getEmployeesAges(40, 120);
        testEmployees(expected, employees40Plus);
    }

    @Test
    void testGetEmployeesSalary() {
        Employee expected[] = {empl1, empl2};
        Iterable<Employee> employeesEmpty =
                employeesService.getEmployeesSalary(10000, 25000);
        assertFalse(employeesEmpty.iterator().hasNext());
        Iterable<Employee> employees1000_2500 =
                employeesService.getEmployeesSalary(1000, 2500);
        testEmployees(expected, employees1000_2500);
    }

    @Test
    void testUpdateCompany() {
        assertEquals(EmployeesReturnCodes.OK,
                employeesService.updateCompany(ID1, "company"));
        Employee empl = employeesService.getEmployee(ID1);
        assertNotEquals(empl1, empl);
        assertEquals("company", empl.getCompany());
    }

    @Test
    void testUpdateSalary() {
        int newSalary = 100000;
        assertEquals(EmployeesReturnCodes.OK,
                employeesService.updateSalary(ID1, newSalary ));
        Employee empl = employeesService.getEmployee(ID1);
        assertNotEquals(empl1, empl);
        assertEquals(newSalary, empl.getSalary());
    }

}

