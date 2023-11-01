package com.individualproject.church.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message="Укажите дату покупки")
    @PastOrPresent
    private LocalDate purchaseDate;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Church church;
    @ManyToOne
    private Employee employee;
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    private Collection<Estimate> estimateCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Church getChurch() {
        return church;
    }

    public void setChurch(Church church) {
        this.church = church;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Collection<Estimate> getEstimateCollection() {
        return estimateCollection;
    }

    public void setEstimateCollection(Collection<Estimate> estimateCollection) {
        this.estimateCollection = estimateCollection;
    }

    public Receipt(LocalDate purchaseDate, Product product, Church church, Employee employee, Collection<Estimate> estimateCollection) {
        this.purchaseDate = purchaseDate;
        this.product = product;
        this.church = church;
        this.employee = employee;
        this.estimateCollection = estimateCollection;
    }

    public Receipt() {
    }
}
