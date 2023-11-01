package com.individualproject.church.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
public class Church {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Это поле не может быть пустым")
    private String churchName;
    @NotEmpty(message = "Это поле не может быть пустым")
    private String churchAddress;
    @OneToMany(mappedBy = "church", cascade = CascadeType.ALL)
    private Collection<Department> departmentCollection;
    @OneToMany(mappedBy = "church", cascade = CascadeType.ALL)
    private Collection<Warehouse> warehouseCollection;
    @OneToMany(mappedBy = "church", cascade = CascadeType.ALL)
    private Collection<Receipt> receiptCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChurchName() {
        return churchName;
    }

    public void setChurchName(String churchName) {
        this.churchName = churchName;
    }

    public String getChurchAddress() {
        return churchAddress;
    }

    public void setChurchAddress(String churchAddress) {
        this.churchAddress = churchAddress;
    }

    public Collection<Department> getDepartmentCollection() {
        return departmentCollection;
    }

    public void setDepartmentCollection(Collection<Department> departmentCollection) {
        this.departmentCollection = departmentCollection;
    }

    public Collection<Warehouse> getWarehouseCollection() {
        return warehouseCollection;
    }

    public void setWarehouseCollection(Collection<Warehouse> warehouseCollection) {
        this.warehouseCollection = warehouseCollection;
    }

    public Collection<Receipt> getReceiptCollection() {
        return receiptCollection;
    }

    public void setReceiptCollection(Collection<Receipt> receiptCollection) {
        this.receiptCollection = receiptCollection;
    }

    public Church(String churchName, String churchAddress, Collection<Department> departmentCollection, Collection<Warehouse> warehouseCollection, Collection<Receipt> receiptCollection) {
        this.churchName = churchName;
        this.churchAddress = churchAddress;
        this.departmentCollection = departmentCollection;
        this.warehouseCollection = warehouseCollection;
        this.receiptCollection = receiptCollection;
    }

    public Church() {
    }
}
