package com.marekpoliszak.Spring.Events.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class EventCategory extends AbstractEvent{

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;


    public EventCategory(String name) {
        this.name = name;
    }

    public EventCategory() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
