package com.individualproject.church.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Это поле не может быть пустым")
    private String surname;
    @NotEmpty(message = "Это поле не может быть пустым")
    private String name;
    private String patronymic;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message="Укажите дату рождения")
    @Past
    private LocalDate birthday;
    @ManyToOne
    private Position position;
    @ManyToOne
    private Department department;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Collection<Receipt> receiptCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Collection<Receipt> getReceiptCollection() {
        return receiptCollection;
    }

    public void setReceiptCollection(Collection<Receipt> receiptCollection) {
        this.receiptCollection = receiptCollection;
    }

    public Employee(String surname, String name, String patronymic, LocalDate birthday, Position position, Department department, Collection<Receipt> receiptCollection) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.position = position;
        this.department = department;
        this.receiptCollection = receiptCollection;
    }

    public Employee() {
    }
}
