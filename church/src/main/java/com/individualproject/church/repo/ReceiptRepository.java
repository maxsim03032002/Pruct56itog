package com.individualproject.church.repo;

import com.individualproject.church.models.Receipt;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
}
