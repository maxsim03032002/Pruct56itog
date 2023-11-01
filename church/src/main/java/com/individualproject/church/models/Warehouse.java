package com.individualproject.church.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Это поле не может быть пустым")
    private String warehouseName;
    @NotEmpty(message = "Это поле не может быть пустым")
    private String warehouseAddress;
    @ManyToOne
    private Church church;
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private Collection<Product> productCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public Church getChurch() {
        return church;
    }

    public void setChurch(Church church) {
        this.church = church;
    }

    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    public Warehouse(String warehouseName, String warehouseAddress, Church church, Collection<Product> productCollection) {
        this.warehouseName = warehouseName;
        this.warehouseAddress = warehouseAddress;
        this.church = church;
        this.productCollection = productCollection;
    }

    public Warehouse() {
    }
}
