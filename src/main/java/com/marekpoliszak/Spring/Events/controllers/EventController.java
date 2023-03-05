package com.marekpoliszak.Spring.Events.controllers;

import com.marekpoliszak.Spring.Events.data.EventCategoryRepository;
import com.marekpoliszak.Spring.Events.data.EventRepository;
import com.marekpoliszak.Spring.Events.model.Event;
import com.marekpoliszak.Spring.Events.model.EventCategory;
import com.marekpoliszak.Spring.Events.model.EventType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;


    //All events in browser table
    @RequestMapping
    public String displayEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    //Return all events in JSON
    @RequestMapping("json")
    @ResponseBody
    public Iterable<Event> GetAllEvents() {
        return eventRepository.findAll();
    }

    @RequestMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create Events");
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    //Create event by form
    @RequestMapping(method = {RequestMethod.POST}, value = "create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors,
                                         Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Events");
            System.out.println(errors.getAllErrors());
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:/events";
    }

    //Create event by sending JSON
    @RequestMapping(method = RequestMethod.POST,  value = "create/json")
    @ResponseBody
    public Event addEvent(@RequestBody @Valid Event event) {
        if(eventRepository.existsById(event.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Event already exists");
        }

        Event newEvent = this.eventRepository.save(event);
        return newEvent;
    }


    @RequestMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    //Method to delete by parameter, return thymeleaf template
    @RequestMapping(method = {RequestMethod.POST}, value = "delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventsIds, Model model) {

        if (eventsIds != null) {
            for (int id : eventsIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:/events";
    }

    //Method to delete by parameter, return JSON
    @RequestMapping(method = RequestMethod.DELETE, value = "delete/json")
    @ResponseBody
    public List<Event> deleteEvent(@RequestParam(required = false, name = "id") int[] eventsIds) {
        List<Event> events = new ArrayList<>();
        for (Integer eventId: eventsIds) {
            Optional<Event> eventToDeleteOptional = eventRepository.findById(eventId);
            if(eventToDeleteOptional.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
            }
            Event eventToDelete = eventToDeleteOptional.get();
            events.add(eventToDelete);
            this.eventRepository.delete(eventToDelete);
        }
        return events;
    }

    //Method to update event's fields
    @RequestMapping(method = RequestMethod.PUT, value = "update/json")
    @ResponseBody
    public Event updateEvent(@RequestBody Event event) {
        Optional<Event> eventOptional = eventRepository.findById(event.getId());
        if(eventOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }

        return this.eventRepository.save(event);
    }
}