package com.individualproject.church.controllers;

import com.individualproject.church.models.Account;
import com.individualproject.church.models.Church;
import com.individualproject.church.models.Department;
import com.individualproject.church.models.Position;
import com.individualproject.church.repo.AccountRepository;
import com.individualproject.church.repo.ChurchRepository;
import com.individualproject.church.repo.DepartmentRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller("/department")
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ChurchRepository churchRepository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("department", departmentRepository.findAll());
        return "department";
    }
    @GetMapping("add")
    public String add(Department department, Model model){
        Iterable<Church> church = churchRepository.findAll();
        model.addAttribute("church", church);
        return "department-add";
    }

    @PostMapping("add")
    public String add(@Valid Department department, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("church", churchRepository.findAll());
            return "department-add";
        }
        departmentRepository.save(department);
        return "redirect:/department";
    }
    @GetMapping("{id}/remove")
    public String remove(@PathVariable Long id){
        departmentRepository.deleteById(id);
        return "redirect:/department";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("church", churchRepository.findAll());
        model.addAttribute("department", departmentRepository.findById(id).orElseThrow());
        return "department-edit";
    }
    @PostMapping("{id}/edit")
    public String edit(@PathVariable Long id, @Valid Department department, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("church", churchRepository.findAll());
            return "department-edit";
        }
        departmentRepository.save(department);
        return "redirect:/department";
    }
}
