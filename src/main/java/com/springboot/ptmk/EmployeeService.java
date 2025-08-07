package com.springboot.ptmk;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    /*private Employee findEmployeeById(Long id) {
        final String errorMessage = String.format("Employee with id %d not found.", id);

        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(errorMessage));
    }*/

    public Employee addEmployee(String fullName, LocalDate birthDate, String sex) {
        Employee employee = new Employee(fullName, birthDate, sex);

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllDistinctEmployeesSortedByFullName() { // With a unique combination of employee's full name and date of birth
        return employeeRepository.findDistinctEmployeesByFullNameAndBirthDate();
    }

    public int calculateAgeFromBirthDate(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @Transactional
    public void addOneMillionOneHundredEmployees() {
        List<Employee> employees = EmployeeGenerator.generateEmployees();
        // long start = System.currentTimeMillis();
        saveEmployeesInBatches(employees);
        /*long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " ms");*/
    }

    @Transactional
    private void saveEmployeesInBatches(List<Employee> employees) {
        final int batchSize = 1000;
        for (int i = 0; i < employees.size(); i += batchSize) {
            int end = Math.min(i + batchSize, employees.size());
            List<Employee> batch = employees.subList(i, end);
            employeeRepository.saveAll(batch);
            entityManager.flush();
            entityManager.clear();
        }
    }

    public List<Employee> getAllMaleEmployeesWithFLastName() {
        return employeeRepository.findMaleWithFLastName();
    }
}
