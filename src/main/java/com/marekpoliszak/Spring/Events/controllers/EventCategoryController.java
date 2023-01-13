package com.marekpoliszak.Spring.Events.controllers;


import com.marekpoliszak.Spring.Events.data.EventCategoryRepository;
import com.marekpoliszak.Spring.Events.model.EventCategory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("categories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @RequestMapping
    public String displayCategories(Model model) {
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "categories/index";
    }

    @RequestMapping("create")
    public String renderCreateCategoryForm(Model model) {
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());
        return "categories/create";
    }

    @RequestMapping(method = RequestMethod.POST, value = "create")
    public String processCreateCategoryForm(@ModelAttribute @Valid EventCategory newCategory,
                                            Model model, Errors errors) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            return "categories/create";
        }
        eventCategoryRepository.save(newCategory);
        return "redirect:/categories";
    }



}
