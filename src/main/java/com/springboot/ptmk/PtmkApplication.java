package com.springboot.ptmk;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class PtmkApplication implements CommandLineRunner {

	private final EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(PtmkApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (args.length > 0) {
			int mode;

			try {
				mode = Integer.parseInt(args[0]);

				switch (mode) {
					case 1 -> System.out.println("Table creation is handled automatically via JPA.");
					case 2 -> {
						String fullName = args[1];

						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate birthDate = LocalDate.parse(args[2], formatter);

						String sex = args[3];

						System.out.println(employeeService.addEmployee(fullName, birthDate, sex).toString());
					}
					case 3 -> {
						List<Employee> employeeList = employeeService.getAllDistinctEmployeesSortedByFullName();

						System.out.printf("%-5s %-60s %-15s %-10s %-15s%n", "ID", "ФИО", "Дата рождения", "Пол", "Кол-во полных лет");

						for (Employee e : employeeList) {
							int age = employeeService.calculateAgeFromBirthDate(e.getBirthDate());
							System.out.printf("%-5d %-60s %-15s %-10s %-5d%n",
									e.getId(),
									e.getFullName(),
									e.getBirthDate(),
									e.getSex(),
									age);
						}
					}
					case 4 -> employeeService.addOneMillionOneHundredEmployees();
					case 5 -> {
						long startTime = System.currentTimeMillis();

						List<Employee> employeeList = employeeService.getAllMaleEmployeesWithFLastName();

						long endTime = System.currentTimeMillis();
						System.out.println("Found " + employeeList.size() + " employees");

						System.out.println("Execution time: " + (endTime - startTime) + " ms");
					}
					default -> {
						System.err.println("Unknown command line argument, try again.");
						System.exit(3);
					}
				}
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[0] + " must be an integer.");
				System.exit(2);
			}
		} else {
			System.err.println("Command line arguments were not provided, try again.");
			System.exit(1);
		}
	}
}
