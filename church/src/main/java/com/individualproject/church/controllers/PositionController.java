package com.individualproject.church.controllers;

import com.individualproject.church.models.Account;
import com.individualproject.church.models.Employee;
import com.individualproject.church.models.Position;
import com.individualproject.church.repo.EmployeeRepository;
import com.individualproject.church.repo.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller("/position")
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionRepository positionRepository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("position", positionRepository.findAll());
        return "position";
    }
    @GetMapping("add")
    public String add(Position position){ return "position-add"; }

    @PostMapping("add")
    public String add(@Valid Position position, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "position-add";
        }
        positionRepository.save(position);
        return "redirect:/position";
    }
    @GetMapping("{id}/remove")
    public String remove(@PathVariable Long id){
        positionRepository.deleteById(id);
        return "redirect:/position";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("position", positionRepository.findById(id).orElseThrow());
        return "position-edit";
    }
    @PostMapping("{id}/edit")
    public String edit(@PathVariable Long id, @Valid Position position, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "position-edit";
        }
        positionRepository.save(position);
        return "redirect:/position";
    }
}
