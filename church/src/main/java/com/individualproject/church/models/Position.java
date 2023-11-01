package com.individualproject.church.models;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Это поле не может быть пустым")
    private String positionName;
    @DecimalMin(value = "0")
    private Double salary;
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    private Collection<Employee> employeeCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    public Position(String positionName, Double salary, Collection<Employee> employeeCollection) {
        this.positionName = positionName;
        this.salary = salary;
        this.employeeCollection = employeeCollection;
    }

    public Position() {
    }
}
