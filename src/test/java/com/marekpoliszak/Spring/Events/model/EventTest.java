package com.marekpoliszak.Spring.Events.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    EventCategory eventCategory = new EventCategory("Social");
    Event event = new Event("Hackathon", "Marathon of coding", "hack@hack.com", "2023/01/14", eventCategory);

    @Test
    void createTest() {
        assertEquals("Hackathon", event.getName());
        assertEquals("Marathon of coding", event.getDescription());
        assertEquals("hack@hack.com", event.getEmail());
        assertEquals("2023/01/14", event.getDate());
    }
}