package com.individualproject.church.repo;

import com.individualproject.church.models.Calendar;
import org.springframework.data.repository.CrudRepository;

public interface CalendarRepository extends CrudRepository<Calendar, Long> {
}
