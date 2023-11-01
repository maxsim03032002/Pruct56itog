package com.individualproject.church.controllers;

import com.individualproject.church.models.Account;
import com.individualproject.church.models.Role;
import com.individualproject.church.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class RegistrationController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registration(Account account){
        return "registration";
    }

    @PostMapping("/registration")
    public String addAccount(Account account, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registration";
        }
        List<Account> result = accountRepository.findByUsername(account.getUsername());
        if(!result.isEmpty()){
            model.addAttribute("message", "Такой пользователь уже существует");
            return "registration";
        }
        account.setActive(true);
        account.setRole(Collections.singleton(Role.USER));
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return "redirect:/login";
    }
}
