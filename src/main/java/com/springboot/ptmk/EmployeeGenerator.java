package com.springboot.ptmk;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EmployeeGenerator {

    static Random random = new Random();

    private static final String[] MALE_FIRST_NAMES = {"Ivan", "Petr", "Sergey", "Fedor", "Dmitry", "Pavel"};
    private static final String[] MALE_MIDDLE_NAMES = {"Sergeevich", "Ivanovich", "Dmitrievich", "Petrovich", "Fedorovich", "Pavlovich"};
    private static final String[] MALE_LAST_NAMES = {"Ivanov", "Petrov", "Sidorov", "Fedorov", "Fadeev", "Famusov", "Fedorchuk", "Frunze"};

    private static final String[] FEMALE_FIRST_NAMES = {"Anna", "Elena", "Irina", "Olga", "Maria", "Sofia"};
    private static final String[] FEMALE_MIDDLE_NAMES = {"Sergeevna", "Ivanovna", "Dmitrievna", "Petrovna", "Fedorovna", "Pavlovna"};
    private static final String[] FEMALE_LAST_NAMES = {"Ivanova", "Petrova", "Sidorova", "Fedorova"};

    public static List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>(1_000_100);

        for (int i = 0; i < 1_000_000; i++) {
            employees.add(generateRandomEmployee());
        }

        for (int i = 0; i < 100; i++) {
            employees.add(generateMaleEmployeeWithFLastName());
        }

        return employees;
    }

    private static Employee generateRandomEmployee() {
        String sex = random.nextBoolean() ? "Male" : "Female";

        String firstName;
        String middleName;
        String lastName;

        if (sex.equals("Male")) {
            firstName = getRandom(MALE_FIRST_NAMES);
            middleName = getRandom(MALE_MIDDLE_NAMES);
            lastName = getRandom(MALE_LAST_NAMES);
        } else {
            firstName = getRandom(FEMALE_FIRST_NAMES);
            middleName = getRandom(FEMALE_MIDDLE_NAMES);
            lastName = getRandom(FEMALE_LAST_NAMES);
        }

        String fullName = lastName + " " + firstName + " " + middleName;
        LocalDate birthDate = generateRandomBirthDate();

        return new Employee(fullName, birthDate, sex);
    }

    private static Employee generateMaleEmployeeWithFLastName() {
        String firstName = getRandom(MALE_FIRST_NAMES);
        String middleName = getRandom(MALE_MIDDLE_NAMES);

        /*List<String> fLastNames = new ArrayList<>();
        for (String ln : MALE_LAST_NAMES) {
            if (ln.startsWith("F")) {
                fLastNames.add(ln);
            }
        }*/

        List<String> fLastNames = Arrays.stream(MALE_LAST_NAMES)
                .filter(name -> name.startsWith("F"))
                .toList();
        String lastName = getRandom(fLastNames.toArray(new String[0]));

        String fullName = lastName + " " + firstName + " " + middleName;
        LocalDate birthDate = generateRandomBirthDate();

        return new Employee(fullName, birthDate, "Male");
    }

    private static LocalDate generateRandomBirthDate() {
        int startYear = 1970;
        int endYear = 2000;
        int year = startYear + random.nextInt(endYear - startYear + 1);
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(28);

        return LocalDate.of(year, month, day);
    }

    private static String getRandom(String[] array) {
        return array[random.nextInt(array.length)];
    }
}
