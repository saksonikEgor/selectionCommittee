package com.saksonik.selectionCommittee.controllers;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.models.Program;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//        System.out.println("Учасвтвует в списках:");
//        programService.getAllByEnrolleeParticipate(enrolleeService.getEnrolleeById(6)).forEach(System.out::println);
//        System.out.println("Может учавствовать в списках:");
//        programService.getAllByEnrolleeNotParticipate(enrolleeService.getEnrolleeById(6)).forEach(System.out::println);
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
        model.addAttribute("pageCantBeOpen", false);
        return "lk/lkPage";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(@ModelAttribute("enrollee") Enrollee enrollee,
                                      Model model) {
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

        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllProgramEnrolleeByEnrollee(enrollee));
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("pageCantBeOpen", false);
        return "lk/lkPage";
    }

    @GetMapping("/participateInAnother{id}")
    public String getParticipatePage(@PathVariable("id") int id, Model model) {
        Enrollee enrollee = enrolleeService.getEnrolleeById(id);

        if (programService.getAllByEnrolleeNotParticipate(enrollee).isEmpty()) {
            model.addAttribute("programEnrollees",
                    programEnrolleeService.getAllProgramEnrolleeByEnrollee(enrollee));
            model.addAttribute("enrollee", enrollee);
            model.addAttribute("pageCantBeOpen", true);

            return "lk/lkPage";
        }

        model.addAttribute("programs",
                programService.getAllByEnrolleeNotParticipate(enrollee));
        model.addAttribute("enrollee", enrollee);

        return "lk/takeParticipatePage";
    }

    @GetMapping("/takeParticipate/enrollee{enrolleeId}/program{programId}")
    public String takeParticipate(@PathVariable("enrolleeId") int enrolleeId,
                              @PathVariable("programId") int programId, Model model) {

        Enrollee enrollee = enrolleeService.getEnrolleeById(enrolleeId);
        Program program = programService.getById(programId);

        programEnrolleeService.saveProgramEnrollee(enrollee, program);

        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllProgramEnrolleeByEnrollee(enrollee));
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("pageCantBeOpen", false);

        return "lk/lkPage";
    }

    @GetMapping("/stopParticipate{id}")
    public String getStopParticipatePage(@PathVariable("id") int id, Model model) {
        Enrollee enrollee = enrolleeService.getEnrolleeById(id);

        model.addAttribute("programs",
                programService.getAllByEnrolleeParticipate(enrollee));
        model.addAttribute("enrollee", enrollee);

        return "lk/stopParticipatePage";
    }

    @GetMapping("/stopParticipate/enrollee{enrolleeId}/program{programId}")
    public String stopParticipate(@PathVariable("enrolleeId") int enrolleeId,
                              @PathVariable("programId") int programId, Model model) {

        Enrollee enrollee = enrolleeService.getEnrolleeById(enrolleeId);
        Program program = programService.getById(programId);

        programEnrolleeService.deleteProgramEnrollee(enrollee, program);

        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllProgramEnrolleeByEnrollee(enrollee));
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("pageCantBeOpen", false);

        return "lk/lkPage";
    }
}
