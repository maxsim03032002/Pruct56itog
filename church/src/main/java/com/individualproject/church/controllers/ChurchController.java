package com.individualproject.church.controllers;

import com.individualproject.church.models.Church;
import com.individualproject.church.models.Department;
import com.individualproject.church.repo.ChurchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller("/church")
@RequestMapping("/church")
public class ChurchController {
    @Autowired
    private ChurchRepository churchRepository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("church", churchRepository.findAll());
        return "church";
    }
    @GetMapping("add")
    public String add(Church church){ return "church-add"; }

    @PostMapping("add")
    public String add(@Valid Church church, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "church-add";
        }
        churchRepository.save(church);
        return "redirect:/church";
    }

    @GetMapping("{id}/remove")
    public String remove(@PathVariable Long id){
        churchRepository.deleteById(id);
        return "redirect:/church";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("church", churchRepository.findById(id).orElseThrow());
        return "church-edit";
    }
    @PostMapping("{id}/edit")
    public String edit(@PathVariable Long id, @Valid Church church, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "church-edit";
        }
        churchRepository.save(church);
        return "redirect:/church";
    }
}
