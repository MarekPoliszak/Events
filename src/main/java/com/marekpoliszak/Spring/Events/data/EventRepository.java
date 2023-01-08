package com.marekpoliszak.Spring.Events.data;

import com.marekpoliszak.Spring.Events.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
