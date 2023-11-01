package com.individualproject.church.controllers;

import com.individualproject.church.models.Account;
import com.individualproject.church.models.Role;
import com.individualproject.church.repo.AccountRepository;
import com.individualproject.church.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller("/account")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String list(Model model){
        model.addAttribute("account", accountRepository.findAll());
        return "account";
    }
    @GetMapping("add")
    public String add(Account account){ return "account-add"; }

    @PostMapping("add")
    public String add(@Valid Account account, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "account-add";
        }
        account.setActive(true);
        account.setRole(Collections.singleton(Role.USER));
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return "redirect:/account";
    }

    @GetMapping("{id}/remove")
    public String remove(@PathVariable Long id){
        accountRepository.deleteById(id);
        return "redirect:/account";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        model.addAttribute("account", accountRepository.findById(id).orElseThrow());
        return "account-edit";
    }
    @PostMapping("{id}/edit")
    public String edit(@PathVariable Long id, @Valid Account account, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "account-edit";
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return "redirect:/account";
    }

    @GetMapping("find")
    public String find(Account account){ return "account-find"; }
    @PostMapping("find")
    public String find(@RequestParam String username, Model model){
        List<Account> result = accountRepository.findByUsername(username);
        model.addAttribute("result", result);
        return "account-find";
    }
}
