package com.individualproject.church.repo;

import java.util.List;
import com.individualproject.church.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}
