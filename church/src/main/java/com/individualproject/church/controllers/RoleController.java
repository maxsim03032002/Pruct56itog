package com.individualproject.church.controllers;


import com.individualproject.church.models.Role;
import com.individualproject.church.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.validation.Valid;

/*@Controller("/role")
@RequestMapping("/role")*/
public class RoleController {
    /*@Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("role", roleRepository.findAll());
        return "role";
    }
    @GetMapping("add")
    public String add(Role role){ return "role-add"; }

    @PostMapping("add")
    public String add(@Valid Role role, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "role-add";
        }
        roleRepository.save(role);
        return "redirect:/role";
    }

    @GetMapping("{id}/remove")
    public String remove(@PathVariable Long id){
        roleRepository.deleteById(id);
        return "redirect:/role";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("role", roleRepository.findById(id).orElseThrow());
        return "role-edit";
    }
    @PostMapping("{id}/edit")
    public String edit(@PathVariable Long id, @Valid Role role, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("role", roleRepository.findById(id).orElseThrow());
            return "role-edit";
        }
        roleRepository.save(role);
        return "redirect:/role";
    }*/
}
