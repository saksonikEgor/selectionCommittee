package com.saksonik.selectionCommittee.controllers;

import com.saksonik.selectionCommittee.models.Program;
import com.saksonik.selectionCommittee.services.AchievementService;
import com.saksonik.selectionCommittee.services.EnrolleeService;
import com.saksonik.selectionCommittee.services.ProgramEnrolleeService;
import com.saksonik.selectionCommittee.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/programs")
public class ProgramController {
    private final ProgramService programService;
    private final EnrolleeService enrolleeService;
    private final AchievementService achievementService;
    private final ProgramEnrolleeService programEnrolleeService;

    @Autowired
    public ProgramController(ProgramService programService, EnrolleeService enrolleeService, AchievementService achievementService, ProgramEnrolleeService programEnrolleeService) {
        this.programService = programService;
        this.enrolleeService = enrolleeService;
        this.achievementService = achievementService;
        this.programEnrolleeService = programEnrolleeService;
    }

    @GetMapping("")
    public String getMainPage(Model model) {
        model.addAttribute("programs", programService.getAll());
        return "programs/programsPage";
    }

    @GetMapping("/{id}")
    public String getEnrolles(@PathVariable("id") int id, Model model) {
        Program program = programService.getById(id);

//        System.out.println("programEnrollees: ----------------");
//        programEnrolleeService.getAllByProgram(program).forEach(System.out::println);
//        System.out.println("-------------------------------");
//        enrolleeService.getEnrolleesByProgram(program).forEach(System.out::println);
//        System.out.println("-------------------------------");

        model.addAttribute("program", program);
        model.addAttribute("achievements", achievementService.getAll());
        model.addAttribute("enrollees",
                enrolleeService.getEnrolleesByProgram(program));
        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllByProgram(program));
        model.addAttribute("notPassedEnrollees",
                enrolleeService.findAllWhoDidNotPassByProgram(program));
        model.addAttribute("notPassedAdditionalTestEnrollees",
                enrolleeService.getAllWhoDidNotPassedAdditionalTestByProgram(program));
        return "enrollees/enrolleesByProgram";
    }

//    @GetMapping("/{id}/fill")
//    public String openFillDocsPage(@PathVariable("id") int id,
//                                   @ModelAttribute("enrollee") Enrollee enrollee,
//                                   @ModelAttribute("programSubject") ProgramSubject programSubject,
//                                   @ModelAttribute("enrolleeSubject") EnrolleeSubject enrolleeSubject,
//                                   Model model) {
//        model.addAttribute("programId", id);
//        model.addAttribute("enrollee", enrollee);
//        model.addAttribute("programSubject", programSubject);
//        model.addAttribute("enrolleeSubject", enrolleeSubject);
//        return "enrollees/submitDocsPage";
//    }
}
