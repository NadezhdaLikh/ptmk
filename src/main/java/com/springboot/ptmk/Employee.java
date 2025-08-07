package com.springboot.ptmk;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data // Equivalent to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
@Table(name = "\"Сотрудники\"") // @Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "\"ID\"") // @Column(name = "id")
    private Long id;

    @Column(name = "\"ФИО\"") // @Column(name = "full_name")
    private String fullName;

    @Column(name = "\"Дата рождения\"") // @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "\"Пол\"") // @Column(name = "sex")
    private String sex;

    public Employee() {}

    public Employee(String fullName, LocalDate birthDate, String sex) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.sex = sex;
    }
}
