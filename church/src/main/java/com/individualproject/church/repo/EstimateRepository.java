package com.individualproject.church.repo;

import com.individualproject.church.models.Estimate;
import org.springframework.data.repository.CrudRepository;

public interface EstimateRepository extends CrudRepository<Estimate, Long> {
}
