package com.individualproject.church.controllers;

import com.individualproject.church.models.Department;
import com.individualproject.church.models.Employee;
import com.individualproject.church.models.Position;
import com.individualproject.church.repo.DepartmentRepository;
import com.individualproject.church.repo.EmployeeRepository;
import com.individualproject.church.repo.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller("/employee")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public String list(Model model){
        model.addAttribute("employee", employeeRepository.findAll());
        return "employee";
    }
    @GetMapping("add")
    public String add(Employee employee, Model model){
        Iterable<Position> position = positionRepository.findAll();
        model.addAttribute("position", position);
        Iterable<Department> department = departmentRepository.findAll();
        model.addAttribute("department", department);
        return "employee-add";
    }

    @PostMapping("add")
    public String add(@Valid Employee employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "employee-add";
        }
        employeeRepository.save(employee);
        return "redirect:/employee";
    }
    @GetMapping("{id}/remove")
    public String remove(@PathVariable Long id){
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        Iterable<Position> position = positionRepository.findAll();
        model.addAttribute("position", position);
        Iterable<Department> department = departmentRepository.findAll();
        model.addAttribute("department", department);
        model.addAttribute("employee", employeeRepository.findById(id).orElseThrow());
        return "employee-edit";
    }
    @PostMapping("{id}/edit")
    public String edit(@PathVariable Long id, @Valid Employee employee, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "employee-edit";
        }
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("find")
    public String find(Employee employee){ return "employee-find"; }
    @PostMapping("find")
    public String find(@RequestParam String surname, Model model){
        List<Employee> result = employeeRepository.findBySurname(surname);
        model.addAttribute("result", result);
        return "employee-find";
    }
}
