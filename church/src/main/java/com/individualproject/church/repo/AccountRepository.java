package com.individualproject.church.repo;

import java.util.List;
import com.individualproject.church.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByUsername(String username);
}
