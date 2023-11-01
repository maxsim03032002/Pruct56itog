package com.individualproject.church.controllers;

import com.individualproject.church.models.Church;
import com.individualproject.church.models.Warehouse;
import com.individualproject.church.repo.ChurchRepository;
import com.individualproject.church.repo.WarehouseRepository;
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

@Controller("/warehouse")
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ChurchRepository churchRepository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("warehouse", warehouseRepository.findAll());
        return "warehouse";
    }
    @GetMapping("add")
    public String add(Warehouse warehouse, Model model){
        Iterable<Church> church = churchRepository.findAll();
        model.addAttribute("church", church);
        return "warehouse-add";
    }

    @PostMapping("add")
    public String add(@Valid Warehouse warehouse, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("church", churchRepository.findAll());
            return "warehouse-add";
        }
        warehouseRepository.save(warehouse);
        return "redirect:/warehouse";
    }

    @GetMapping("{id}/remove")
    public String remove(@PathVariable Long id){
        warehouseRepository.deleteById(id);
        return "redirect:/warehouse";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("church", churchRepository.findAll());
        model.addAttribute("warehouse", warehouseRepository.findById(id).orElseThrow());
        return "warehouse-edit";
    }
    @PostMapping("{id}/edit")
    public String edit(@PathVariable Long id, @Valid Warehouse warehouse, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "warehouse-edit";
        }
        warehouseRepository.save(warehouse);
        return "redirect:/warehouse";
    }
}
