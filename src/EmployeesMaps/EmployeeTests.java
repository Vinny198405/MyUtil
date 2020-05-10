package EmployeesMaps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTests {
    private EmployeesService service;
    private Employee marta;

    @BeforeEach
    void initService() {
        service = new EmployeesServiceMapsImpl();
        marta = new Employee(9, 90000, "Google",
                LocalDate.of(1987, 10, 8), "Marta");

        Employee[] data = {
                new Employee(1, 10000, "IBM",
                        LocalDate.of(1991, 10, 15), "John"),
                new Employee(2, 8000, "Microsoft",
                        LocalDate.of(1997, 4, 15), "Maks"),
                new Employee(3, 4000, "IBM",
                        LocalDate.of(1988, 5, 14), "Mike"),
                new Employee(4, 20000, "IBM",
                        LocalDate.of(1987, 11, 25), "Ally"),
                new Employee(5, 22000, "Microsoft",
                        LocalDate.of(1982, 4, 23), "Dina"),
                new Employee(6, 12000, "Google",
                        LocalDate.of(1980, 7, 3), "Dima"),
                new Employee(7, 18000, "Google",
                        LocalDate.of(1984, 8, 15), "Sergey"),
                new Employee(8, 50000, "Google",
                        LocalDate.of(1980, 2, 16), "Mark"),
                marta,
        };

        for (Employee employee : data) {
            assertEquals(EmployeesReturnCodes.OK, service.addEmployee(employee));
        }
    }

    @Test
    void testAdd() {
        assertEquals(EmployeesReturnCodes.EMPLOYEE_ALREADY_EXISTS, service.addEmployee(marta));
    }

    @Test
    void testRemoveAndGet() {
        long idToRemove = marta.getId();
        Employee employee = service.getEmployee(idToRemove);

        assertNotNull(employee);
        assertEquals(marta, employee);

        assertEquals(EmployeesReturnCodes.OK, service.removeEmployee(idToRemove));
        assertEquals(EmployeesReturnCodes.OK, service.removeEmployee(6));
        assertEquals(EmployeesReturnCodes.OK, service.removeEmployee(7));
        assertEquals(EmployeesReturnCodes.OK, service.removeEmployee(8));
        assertNull(service.getEmployee(idToRemove));
        assertEquals(EmployeesReturnCodes.EMPLOYEE_NOT_FOUND, service.removeEmployee(idToRemove));
        Collection<Employee> result = (Collection<Employee>)
                service.getEmployeesCompany("Google");
        assertEquals(0, result.size());
    }

    @Test
    void testUpdateSalary() {
        assertEquals(EmployeesReturnCodes.EMPLOYEE_NOT_FOUND,
                service.updateSalary(90, 3000));
        assertEquals(EmployeesReturnCodes.SAME_SALARY,
                service.updateSalary(1, 10000));
        assertEquals(EmployeesReturnCodes.OK, service.updateSalary(marta.getId(),
                3000));

        Collection<Employee> result = (Collection<Employee>)
                service.getEmployeesSalary(1500, 3500);
        assertEquals(1, result.size());

        assertEquals(EmployeesReturnCodes.OK, service.updateSalary(marta.getId(),
                140000));
        result = (Collection<Employee>) service.getEmployeesSalary(100000,
                200000);
        assertEquals(1, result.size());

        result = (Collection<Employee>) service.getEmployeesSalary(1000,
                3500);
        assertEquals(0, result.size());
    }

    @Test
    void testUpdateCompany() {
        assertEquals(EmployeesReturnCodes.EMPLOYEE_NOT_FOUND,
                service.updateCompany(90, "IBMM"));
        assertEquals(EmployeesReturnCodes.SAME_COMPANY,
                service.updateCompany(1, "IBM"));
        assertEquals(EmployeesReturnCodes.OK, service.updateCompany(marta.getId(),
                "IBMM"));

        Collection<Employee> result = (Collection<Employee>)
                service.getEmployeesCompany("IBMM");
        assertEquals(1, result.size());

        assertEquals(EmployeesReturnCodes.OK, service.updateCompany(marta.getId(),
                "IBM"));
        result = (Collection<Employee>) service.getEmployeesCompany("IBM");
        assertEquals(4, result.size());

        result = (Collection<Employee>) service.getEmployeesCompany("IBMM");
        assertEquals(0, result.size());
    }

    @Test
    void testGetAges() {
        Collection<Employee> result = (Collection<Employee>)
                service.getEmployeesAges(26, 34);
        assertEquals(4, result.size());

        result = (Collection<Employee>) service.getEmployeesAges(80, 120);
        assertEquals(0, result.size());
    }

    @Test
    void testGetSalary() {
        Collection<Employee> result = (Collection<Employee>)
                service.getEmployeesSalary(10000, 20000);
        assertEquals(3, result.size());

        result = (Collection<Employee>)
                service.getEmployeesSalary(1000, 3000);
        assertEquals(0, result.size());
    }

    @Test
    void testGetCompany() {
        Collection<Employee> result = (Collection<Employee>)
                service.getEmployeesCompany("IBM");
        assertEquals(3, result.size());

        result = (Collection<Employee>) service.getEmployeesCompany("IBMM");
        assertEquals(0, result.size());
    }

    @Test
    void testGetAll() {
        Collection<Employee> result = (Collection<Employee>) service.getEmployees();
        assertEquals(9, result.size());
    }
}
