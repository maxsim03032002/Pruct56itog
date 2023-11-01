package com.individualproject.church.repo;

import java.util.List;
import com.individualproject.church.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findBySurname(String surname);
}
