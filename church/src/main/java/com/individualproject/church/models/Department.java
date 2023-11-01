package com.individualproject.church.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Это поле не может быть пустым")
    private String departmentName;
    @ManyToOne
    private Church church;
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Collection<Employee> employeeCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Church getChurch() {
        return church;
    }

    public void setChurch(Church church) {
        this.church = church;
    }

    public Department(String departmentName, Church church) {
        this.departmentName = departmentName;
        this.church = church;
    }

    public Department() {
    }
}
