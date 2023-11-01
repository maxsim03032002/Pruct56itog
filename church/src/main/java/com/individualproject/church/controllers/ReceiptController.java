package com.individualproject.church.controllers;

import com.individualproject.church.models.Church;
import com.individualproject.church.models.Receipt;
import com.individualproject.church.repo.ChurchRepository;
import com.individualproject.church.repo.EmployeeRepository;
import com.individualproject.church.repo.ProductRepository;
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

@Controller("/receipt")
@RequestMapping("/receipt")
public class ReceiptController {
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ChurchRepository churchRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("receipt", receiptRepository.findAll());
        return "receipt";
    }
    @GetMapping("add")
    public String add(Receipt receipt, Model model){
        model.addAttribute("product", productRepository.findAll());
        model.addAttribute("church", churchRepository.findAll());
        model.addAttribute("employee", employeeRepository.findAll());
        return "receipt-add";
    }

    @PostMapping("add")
    public String add(@Valid Receipt receipt, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("product", productRepository.findAll());
            model.addAttribute("church", churchRepository.findAll());
            model.addAttribute("employee", employeeRepository.findAll());
            return "receipt-add";
        }
        receiptRepository.save(receipt);
        return "redirect:/receipt";
    }
    @GetMapping("{id}/remove")
    public String remove(@PathVariable Long id){
        receiptRepository.deleteById(id);
        return "redirect:/receipt";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("product", productRepository.findAll());
        model.addAttribute("church", churchRepository.findAll());
        model.addAttribute("employee", employeeRepository.findAll());
        model.addAttribute("receipt", receiptRepository.findById(id).orElseThrow());
        return "receipt-edit";
    }
    @PostMapping("{id}/edit")
    public String edit(@PathVariable Long id, @Valid Receipt receipt, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("product", productRepository.findAll());
            model.addAttribute("church", churchRepository.findAll());
            model.addAttribute("employee", employeeRepository.findAll());
            model.addAttribute("receipt", receiptRepository.findById(id).orElseThrow());
            return "receipt-edit";
        }
        receiptRepository.save(receipt);
        return "redirect:/receipt";
    }
}
