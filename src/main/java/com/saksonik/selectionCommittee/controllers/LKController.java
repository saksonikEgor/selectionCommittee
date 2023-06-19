package com.saksonik.selectionCommittee.controllers;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.services.EnrolleeService;
import com.saksonik.selectionCommittee.services.ProgramEnrolleeService;
import com.saksonik.selectionCommittee.services.ProgramService;
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

@Controller
@RequestMapping("/lk")
public class LKController {
    private final EnrolleeValidator enrolleeValidator;
    private final EnrolleeService enrolleeService;
    private final SubjectService subjectService;
    private final ProgramService programService;
    private final ProgramEnrolleeService programEnrolleeService;

    @Autowired
    public LKController(EnrolleeValidator enrolleeValidator, EnrolleeService enrolleeService, SubjectService subjectService, ProgramService programService, ProgramEnrolleeService programEnrolleeService) {

        this.enrolleeValidator = enrolleeValidator;
        this.enrolleeService = enrolleeService;
        this.subjectService = subjectService;
        this.programService = programService;
        this.programEnrolleeService = programEnrolleeService;
    }

    @GetMapping("")
    public String getLoginPage(@ModelAttribute("enrollee") Enrollee enrollee) {
//        List<EnrolleeSubject> enrolleeSubjects = enrolleeService.getEnrolleeById(3).getSubjects();
//        List<Subject> subjectsOfEnrollee = new ArrayList<>();
//
//        for (EnrolleeSubject enrolleeSubject : enrolleeSubjects)
//            subjectsOfEnrollee.add(enrolleeSubject.getSubject());
//
//        subjectsOfEnrollee.forEach(System.out::println);
//        programService.getAllBySubjectsContaining(subjectsOfEnrollee).forEach(System.out::println);
        System.out.println("Учасвтвует в списках:");
        programService.getAllByEnrolleeParticipate(enrolleeService.getEnrolleeById(6)).forEach(System.out::println);
        System.out.println("Может учавствовать в списках:");
        programService.getAllByEnrolleeNotParticipate(enrolleeService.getEnrolleeById(6)).forEach(System.out::println);
        return "lk/loginPage";
    }

//    @GetMapping("/editResult")
//    public String getEditResultPage(@ModelAttribute("enrollee") Enrollee enrollee,
//                                    Model model) {
//        List<Subject> subjects = subjectService.getAll();
//        model.addAttribute("subjects", subjects);
//
//
//        List<EnrolleeSubject> enrolleeSubjects = enrollee.getSubjects();
//        for (Subject subject : subjects)
//            enrolleeSubjects.add(new EnrolleeSubject())
//
//        model.addAttribute("enrolleeSubjects", )
//
//    }

    @PostMapping("/afterLogin")
    public String getLkPage(@ModelAttribute("enrollee") @Valid Enrollee enrollee,
                         BindingResult bindingResult, Model model) {

        enrolleeValidator.existValidate(enrollee, bindingResult);

        if (bindingResult.hasErrors())
            return "lk/loginPage";


        Enrollee currentEnrollee = enrolleeService
                .getEnrolleeByEmailAndPassword(enrollee.getEmail(), enrollee.getPassword())
                .get();

        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllProgramEnrolleeByEnrollee(currentEnrollee));
        model.addAttribute("enrollee", currentEnrollee);
        return "lk/lkPage";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("enrollee") Enrollee enrollee,
                                      Model model) {
//        model.addAttribute("subjects", subjectService.getAll());
//        model.addAttribute("profileData", new ProfileData(0));
        model.addAttribute("enrollee", enrollee);
        return "lk/registrationPage";
    }

    @PostMapping("")
    public String saveEnrollee(@ModelAttribute("enrollee") @Valid Enrollee enrollee,
                               BindingResult bindingResult, Model model) {
        enrolleeValidator.validate(enrollee, bindingResult);

        if (bindingResult.hasErrors())
            return "lk/registrationPage";

        enrolleeService.saveEnrollee(enrollee);
//        Enrollee currentEnrollee = enrolleeService
//                .getEnrolleeByEmailAndPassword(enrollee.getEmail(), enrollee.getPassword())
//                .get();

        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllProgramEnrolleeByEnrollee(enrollee));
        model.addAttribute("enrollee", enrollee);
        return "lk/lkPage";
    }
}
