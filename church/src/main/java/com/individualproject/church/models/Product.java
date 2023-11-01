package com.individualproject.church.models;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Это поле не может быть пустым")
    private String productName;
    @Min(value = 1)
    private Long amount;
    @DecimalMin(value = "0")
    private Double price;
    @ManyToOne
    private Warehouse warehouse;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Collection<Receipt> receiptCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Collection<Receipt> getReceiptCollection() {
        return receiptCollection;
    }

    public void setReceiptCollection(Collection<Receipt> receiptCollection) {
        this.receiptCollection = receiptCollection;
    }

    public Product(String productName, Long amount, Double price, Warehouse warehouse, Collection<Receipt> receiptCollection) {
        this.productName = productName;
        this.amount = amount;
        this.price = price;
        this.warehouse = warehouse;
        this.receiptCollection = receiptCollection;
    }

    public Product() {
    }
}
