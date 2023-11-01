package com.individualproject.church.controllers;

import com.individualproject.church.models.Product;
import com.individualproject.church.repo.ProductRepository;
import com.individualproject.church.repo.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller("/product")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("product", productRepository.findAll());
        return "product";
    }
    @GetMapping("add")
    public String add(Product product, Model model){
        model.addAttribute("warehouse", warehouseRepository.findAll());
        return "product-add";
    }

    @PostMapping("add")
    public String add(@Valid Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "product-add";
        }
        productRepository.save(product);
        return "redirect:/product";
    }
    @GetMapping("{id}/remove")
    public String remove(@PathVariable Long id){
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("warehouse", warehouseRepository.findAll());
        model.addAttribute("product", productRepository.findById(id).orElseThrow());
        return "product-edit";
    }
    @PostMapping("{id}/edit")
    public String edit(@PathVariable Long id, @Valid Product product, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "product-edit";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("find")
    public String find(Product product){ return "product-find"; }
    @PostMapping("find")
    public String find(@RequestParam String productName, Model model){
        List<Product> result = productRepository.findByProductName(productName);
        model.addAttribute("result", result);
        return "product-find";
    }
}
