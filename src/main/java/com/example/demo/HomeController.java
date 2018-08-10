package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class HomeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/")
    public String listEmployees(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        return "list";
    }

    @RequestMapping("detail/{id}")
    public String showEmployee(@PathVariable("id") long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "show";
    }

    @PostMapping("/search")
    public String searchEmployee(String lastname, Model model){
        model.addAttribute("employees",employeeRepository.findByLastname(lastname));
        return "list";
    }

    @PostMapping("/search2")
    public String searchDepartment(String department, Model model){
        model.addAttribute("employees",employeeRepository.findDistinctByDepartment(department));
        return "list2";
    }

}
