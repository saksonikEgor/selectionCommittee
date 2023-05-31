package com.saksonik.selectionCommittee.controllers;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.models.EnrolleeSubject;
import com.saksonik.selectionCommittee.models.ProfileData;
import com.saksonik.selectionCommittee.models.Subject;
import com.saksonik.selectionCommittee.services.EnrolleeService;
import com.saksonik.selectionCommittee.services.SubjectService;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lk")
public class LKController {
    private final EnrolleeValidator enrolleeValidator;
    private final EnrolleeService enrolleeService;
    private final SubjectService subjectService;

    @Autowired
    public LKController(EnrolleeValidator enrolleeValidator, EnrolleeService enrolleeService, SubjectService subjectService) {

        this.enrolleeValidator = enrolleeValidator;
        this.enrolleeService = enrolleeService;
        this.subjectService = subjectService;
    }

    @GetMapping("")
    public String getLoginPage(@ModelAttribute("enrollee") Enrollee enrollee) {
        return "lk/loginPage";
    }

    @PostMapping("/afterLogin")
    public String create(@ModelAttribute("enrollee") @Valid Enrollee enrollee,
                         BindingResult bindingResult, Model model) {

        enrolleeValidator.existValidate(enrollee, bindingResult);

        if (bindingResult.hasErrors())
            return "lk/loginPage";

        model.addAttribute("enrollee",
                enrolleeService
                        .getEnrolleeByEmailAndPassword(enrollee.getEmail(), enrollee.getPassword())
                        .get());
        return "lk/lkPage";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("enrollee") Enrollee enrollee,
                                      Model model) {
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("profileData", new ProfileData(0));
        return "lk/registrationPage";
    }

    @PostMapping("")
    public String saveEnrollee(@ModelAttribute("enrollee") @Valid Enrollee enrollee,
                               BindingResult bindingResult, Model model) {
        enrolleeValidator.validate(enrollee, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        enrolleeService.saveEnrollee(enrollee);
        model.addAttribute("enrollee", enrollee);
        return "lk/lkPage";
    }
}
