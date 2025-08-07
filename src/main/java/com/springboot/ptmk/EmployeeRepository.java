package com.springboot.ptmk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = """
            SELECT * 
            FROM (SELECT DISTINCT ON (\"ФИО\", \"Дата рождения\") *
                FROM \"Сотрудники\" 
                ORDER BY \"ФИО\", \"Дата рождения\", \"ID\"
            ) AS distinct_employees
            ORDER BY \"ФИО\"
            """, nativeQuery = true)
    List<Employee> findDistinctEmployeesByFullNameAndBirthDate();

    @Query(value = """
            SELECT *
            FROM \"Сотрудники\" 
            WHERE LOWER(\"Пол\") = 'male' AND \"ФИО\" LIKE 'F%'
            """, nativeQuery = true)
    List<Employee> findMaleWithFLastName();
}
