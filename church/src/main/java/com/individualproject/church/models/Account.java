package com.individualproject.church.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Это поле не может быть пустым")
    private String username;
    @NotEmpty(message = "Это поле не может быть пустым")
    @Size(message = "Пароль должен состоять более, чем из 6 символов", min = 6)
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "account_role", joinColumns = @JoinColumn(name="account_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Account(String username, String password, boolean active, Set<Role> role) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public Account() {
    }
}
