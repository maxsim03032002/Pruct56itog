package com.individualproject.church.controllers;

import com.individualproject.church.models.Estimate;
import com.individualproject.church.repo.EstimateRepository;
import com.individualproject.church.repo.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller("/estimate")
@RequestMapping("/estimate")
public class EstimateController {
    @Autowired
    private EstimateRepository estimateRepository;
    @Autowired
    private ReceiptRepository receiptRepository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("estimate", estimateRepository.findAll());
        return "estimate";
    }
    @GetMapping("add")
    public String add(Estimate estimate, Model model){
        model.addAttribute("receipt", receiptRepository.findAll());
        return "estimate-add";
    }

    @PostMapping("add")
    public String add(@Valid Estimate estimate, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("receipt", receiptRepository.findAll());
            return "estimate-add";
        }
        estimateRepository.save(estimate);
        return "redirect:/estimate";
    }
    @GetMapping("{id}/remove")
    public String remove(@PathVariable Long id){
        estimateRepository.deleteById(id);
        return "redirect:/estimate";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("receipt", receiptRepository.findAll());
        model.addAttribute("estimate", estimateRepository.findById(id).orElseThrow());
        return "estimate-edit";
    }
    @PostMapping("{id}/edit")
    public String edit(@PathVariable Long id, @Valid Estimate estimate, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("receipt", receiptRepository.findAll());
            return "estimate-edit";
        }
        estimateRepository.save(estimate);
        return "redirect:/estimate";
    }
}
