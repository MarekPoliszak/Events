package com.marekpoliszak.Spring.Events.data;

import com.marekpoliszak.Spring.Events.model.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer> {
}
