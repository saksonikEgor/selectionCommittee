package com.saksonik.selectionCommittee.controllers;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.services.EnrolleeService;
import com.saksonik.selectionCommittee.util.EnrolleeValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lk")
public class LKController {
    private final EnrolleeValidator enrolleeValidator;
    private final EnrolleeService enrolleeService;

    @Autowired
    public LKController(EnrolleeValidator enrolleeValidator, EnrolleeService enrolleeService) {

        this.enrolleeValidator = enrolleeValidator;
        this.enrolleeService = enrolleeService;
    }

    @GetMapping("")
    public String getLoginPage(@ModelAttribute("enrollee") Enrollee enrollee) {
        return "lk/loginPage";
    }

    @PostMapping("/afterLogin")
    public String create(@ModelAttribute("enrollee") @Valid Enrollee enrollee,
                         BindingResult bindingResult, Model model) {

        enrolleeValidator.validate(enrollee, bindingResult);

        if (bindingResult.hasErrors())
            return "lk/loginPage";

        model.addAttribute("enrollee",
                enrolleeService
                        .getEnrolleeByEmailAndPassword(enrollee.getEmail(), enrollee.getPassword())
                        .get());
        return "lk/lkPage";
    }
}
