package com.marekpoliszak.Spring.Events.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Event extends AbstractEvent{


    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;

    @Size(max = 500, message = "Description can have maximum 500 characters.")
    private String description;

    @NotBlank(message = "Email is required.")
    @Email(message = "Data must be in proper email format.")
    private String email;

    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

    public Event(String name, String description, String email, EventCategory eventCategory) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.eventCategory = eventCategory;

    }

    public Event(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    @Override
    public String toString() {
        return name;
    }
}
