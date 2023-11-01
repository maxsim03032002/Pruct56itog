package com.individualproject.church.controllers;

import com.individualproject.church.models.Calendar;
import com.individualproject.church.repo.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller("/calendar")
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    private CalendarRepository calendarRepository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("calendar", calendarRepository.findAll());
        return "calendar";
    }
    @GetMapping("add")
    public String add(Calendar calendar){ return "calendar-add"; }

    @PostMapping("add")
    public String add(@Valid Calendar calendar, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "calendar-add";
        }
        calendarRepository.save(calendar);
        return "redirect:/calendar";
    }
    @GetMapping("{id}/remove")
    public String remove(@PathVariable Long id){
        calendarRepository.deleteById(id);
        return "redirect:/calendar";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("calendar", calendarRepository.findById(id).orElseThrow());
        return "calendar-edit";
    }
    @PostMapping("{id}/edit")
    public String edit(@PathVariable Long id, @Valid Calendar calendar, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "calendar-edit";
        }
        calendarRepository.save(calendar);
        return "redirect:/calendar";
    }
}
