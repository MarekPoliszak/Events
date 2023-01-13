package com.marekpoliszak.Spring.Events.controllers;

import com.marekpoliszak.Spring.Events.data.EventCategoryRepository;
import com.marekpoliszak.Spring.Events.data.EventRepository;
import com.marekpoliszak.Spring.Events.model.Event;
import com.marekpoliszak.Spring.Events.model.EventType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;


    @RequestMapping
    public String displayEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    @RequestMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create Events");
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

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

    @RequestMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @RequestMapping(method = {RequestMethod.POST}, value = "delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventsIds, Model model) {

        if (eventsIds != null) {
            for (int id : eventsIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:/events";
    }
}