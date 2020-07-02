package _40_HW_EmployeesMapsStreams.items;

import _40_HW_EmployeesMapsStreams.api.EmployeesService;
import _40_HW_EmployeesMapsStreams.dto.Employee;
import _40_HW_EmployeesMapsStreams.dto.EmployeesReturnCodes;
import _39_HW_MenuItemsInputOutput.menu.InputOutput;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class RandomGeneration extends EmployeesItem {
    private static int id = 100000;
    private static int MIN_SALARY;
    private static int MAX_SALARY;
    private static int N_COMPANIES;
    private static int N_EMPLOYEES;
    private static LocalDate MIN_YEAR_EMPLOYEE;
    private static LocalDate MAX_YEAR_EMPLOYEE;

    public RandomGeneration(EmployeesService employees, InputOutput inputOutput) {
        super(employees, inputOutput);
    }

    @Override
    public String displayName() {
        return "Generation employees";
    }

    @Override
    public void perform() {
        N_EMPLOYEES = inputOutput.inputInteger("Enter the number of employees");
        N_COMPANIES = inputOutput.inputInteger("Enter the number of companies");
        MIN_SALARY = inputOutput.inputInteger("Enter min salary");
        MAX_SALARY = inputOutput.inputInteger("Enter max salary");
        if (MIN_SALARY > MAX_SALARY) {
            inputOutput.displayLine("the maximum salary should be greater than the minimum");
            return;
        }
        MIN_YEAR_EMPLOYEE = inputOutput.inputDate("Enter from birth date YYYY-MM-DD");
        MAX_YEAR_EMPLOYEE = inputOutput.inputDate("Enter to birth date YYYY-MM-DD");
        getRandomPersons();
    }

    private void getRandomPersons() {
        HashMap<EmployeesReturnCodes, Integer> res = new HashMap<>();
        Stream.generate(RandomGeneration::getRandomEmployee).distinct()
                .limit(N_EMPLOYEES).forEach(e -> {
            EmployeesReturnCodes emp = employees.addEmployee(e);
            res.merge(emp, 1, Integer::sum);
        });
        inputOutput.displayLine(res);
    }

    private static Employee getRandomEmployee() {
        LocalDate birthDate = getBirthDay(MIN_YEAR_EMPLOYEE, MAX_YEAR_EMPLOYEE);
        String company = "company" + getRandomNumber(1, N_COMPANIES);
        String name = "name" + getRandomNumber(1, N_EMPLOYEES);
        int salary = getRandomNumber(MIN_SALARY, MAX_SALARY);
        return new Employee(id++, salary, company, birthDate, name);
    }

    private static int getRandomNumber(int min, int max) {
        return (int) (min + Math.random() * (max - min));
    }

    private static LocalDate getBirthDay(LocalDate min, LocalDate max) {
        if (min.isAfter(max)){
            LocalDate temp = min;
            min = max;
            max = temp;
        }
        long startEpochDay = min.toEpochDay();
        long endEpochDay = max.toEpochDay();
        long randomDate = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDate);
    }
}

