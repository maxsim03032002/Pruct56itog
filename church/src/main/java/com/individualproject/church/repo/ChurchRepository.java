package com.individualproject.church.repo;

import com.individualproject.church.models.Church;
import org.springframework.data.repository.CrudRepository;

public interface ChurchRepository extends CrudRepository<Church, Long> {
}
