package com.saksonik.selectionCommittee.controllers;

import com.saksonik.selectionCommittee.models.*;
import com.saksonik.selectionCommittee.services.*;
import com.saksonik.selectionCommittee.util.EnrolleeValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/lk")
public class LKController {
    private final EnrolleeValidator enrolleeValidator;
    private final EnrolleeService enrolleeService;
    private final SubjectService subjectService;
    private final ProgramService programService;
    private final ProgramEnrolleeService programEnrolleeService;
    private final AchievementService achievementService;
    private final EnrolleeAchievementService enrolleeAchievementService;
    private final EnrolleeSubjectService enrolleeSubjectService;

    @Autowired
    public LKController(EnrolleeValidator enrolleeValidator, EnrolleeService enrolleeService, SubjectService subjectService, ProgramService programService, ProgramEnrolleeService programEnrolleeService, AchievementService achievementService, EnrolleeAchievementService enrolleeAchievementService, EnrolleeSubjectService enrolleeSubjectService) {

        this.enrolleeValidator = enrolleeValidator;
        this.enrolleeService = enrolleeService;
        this.subjectService = subjectService;
        this.programService = programService;
        this.programEnrolleeService = programEnrolleeService;
        this.achievementService = achievementService;
        this.enrolleeAchievementService = enrolleeAchievementService;
        this.enrolleeSubjectService = enrolleeSubjectService;
    }

    @GetMapping("")
    public String getLoginPage(@ModelAttribute("enrollee") Enrollee enrollee) {
        return "lk/loginPage";
    }

    @GetMapping("/selectSubjects{id}")
    public String getSelectSubjectsPage(@PathVariable("id") int id,
                                        Model model) {
        Enrollee enrollee = enrolleeService.getEnrolleeById(id);

        model.addAttribute("enrollee", enrollee);
        model.addAttribute("subjects", subjectService.getAll());
//        model.addAttribute("enrolleeSubjects", enrollee.getSubjects());

        return "lk/result/selectSubjectsPage";
    }

    @PostMapping("/editResult{id}")
    public String getEditResultPage(@PathVariable("id") int id,
                                    @RequestParam("selectedSubjects") Integer[] subjectsIdArray,
                                    Model model) {
        Enrollee enrollee = enrolleeService.getEnrolleeById(id);
        List<Subject> subjects = subjectService.getAllByIdArray(subjectsIdArray);

        enrolleeSubjectService.resetEnrolleeSubjectsForEnrollee(enrollee, subjects);

        model.addAttribute("enrollee", enrollee);
        model.addAttribute("subjects", subjects);

        return "lk/result/editResultPage";
    }

    @PostMapping("/saveResult{id}")
    public String saveResult(@PathVariable("id") int id,
                             @RequestParam("results") Integer[] results,
                             Model model) {
        Enrollee enrollee = enrolleeService.getEnrolleeById(id);

        enrolleeSubjectService.setNewResults(enrollee, results);
        programEnrolleeService.leaveCompetitiveListInWhichTheEnrolleeCannotParticipate(enrollee);

        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllProgramEnrolleeByEnrolleeAndExamResultAboveZero(enrollee));
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("pageCantBeOpen", false);
        return "lk/lkPage";
    }

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
                programEnrolleeService.getAllProgramEnrolleeByEnrolleeAndExamResultAboveZero(currentEnrollee));
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

        enrolleeValidator.existValidateForRegistration(enrollee, bindingResult);
        if (bindingResult.hasErrors())
            return "lk/registrationPage";

        enrolleeService.saveEnrollee(enrollee);

        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllProgramEnrolleeByEnrolleeAndExamResultAboveZero(enrollee));
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("pageCantBeOpen", false);
        return "lk/lkPage";
    }

    @GetMapping("/participateInAnother{id}")
    public String getParticipatePage(@PathVariable("id") int id, Model model) {
        Enrollee enrollee = enrolleeService.getEnrolleeById(id);

        if (programService.getAllByEnrolleeNotParticipate(enrollee).isEmpty()) {
            model.addAttribute("programEnrollees",
                    programEnrolleeService.getAllProgramEnrolleeByEnrolleeAndExamResultAboveZero(enrollee));
            model.addAttribute("enrollee", enrollee);
            model.addAttribute("pageCantBeOpen", true);

            return "lk/lkPage";
        }

        model.addAttribute("programs",
                programService.getAllByEnrolleeNotParticipate(enrollee));
        model.addAttribute("enrollee", enrollee);

        return "lk/competitiveList/takeParticipatePage";
    }

    @GetMapping("/takeParticipate/enrollee{enrolleeId}/program{programId}")
    public String takeParticipate(@PathVariable("enrolleeId") int enrolleeId,
                                  @PathVariable("programId") int programId, Model model) {

        Enrollee enrollee = enrolleeService.getEnrolleeById(enrolleeId);
        Program program = programService.getById(programId);

        programEnrolleeService.saveProgramEnrollee(enrollee, program);

        model.addAttribute("enrollee", enrollee);

        if (program.isNeedTest()) {
            model.addAttribute("program", program);

            return "lk/competitiveList/editAdditionalTestResultPage";
        }


        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllProgramEnrolleeByEnrolleeAndExamResultAboveZero(enrollee));
        model.addAttribute("pageCantBeOpen", false);

        return "lk/lkPage";
    }

    @GetMapping("/stopParticipate{id}")
    public String getStopParticipatePage(@PathVariable("id") int id, Model model) {
        Enrollee enrollee = enrolleeService.getEnrolleeById(id);

        model.addAttribute("programs",
                programService.getAllByEnrolleeParticipate(enrollee));
        model.addAttribute("enrollee", enrollee);

        return "lk/competitiveList/stopParticipatePage";
    }

    @GetMapping("/stopParticipate/enrollee{enrolleeId}/program{programId}")
    public String stopParticipate(@PathVariable("enrolleeId") int enrolleeId,
                                  @PathVariable("programId") int programId, Model model) {

        Enrollee enrollee = enrolleeService.getEnrolleeById(enrolleeId);
        Program program = programService.getById(programId);

        programEnrolleeService.deleteProgramEnrollee(enrollee, program);

        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllProgramEnrolleeByEnrolleeAndExamResultAboveZero(enrollee));
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("pageCantBeOpen", false);

        return "lk/lkPage";
    }

    @PostMapping("/saveAdditionalTestResult/enrollee{enrolleeId}/program{programId}")
    public String saveAdditionalTestResult(@PathVariable("enrolleeId") int enrolleeId,
                                           @PathVariable("programId") int programId,
                                           @RequestParam("result") Integer result,
                                           Model model) {
        Enrollee enrollee = enrolleeService.getEnrolleeById(enrolleeId);
        Program program = programService.getById(programId);

        programEnrolleeService.setAdditionalTestResultByProgramAndEnrollee(program, enrollee, result);
        ;

        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllProgramEnrolleeByEnrolleeAndExamResultAboveZero(enrollee));
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("pageCantBeOpen", false);

        return "lk/lkPage";
    }

    @GetMapping("/changeAchievements{id}")
    public String getChangeAchievementPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("enrollee", enrolleeService.getEnrolleeById(id));
        model.addAttribute("achievements", achievementService.getAll());

        return "lk/achievements/changeAchievementsPage";
    }

    @PostMapping("/saveAchievements{id}")
    public String saveAchievements(@PathVariable("id") int id,
                                   @RequestParam(value = "medal") String medal,
                                   @RequestParam(value = "sign") String sign,
                                   Model model) {
        Enrollee enrollee = enrolleeService.getEnrolleeById(id);

        enrolleeAchievementService.resetEnrolleeAchievementsByEnrolleeAndAchievements(enrollee,
                achievementService.getAllByNames(List.of(medal, sign)));

        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllProgramEnrolleeByEnrolleeAndExamResultAboveZero(enrollee));
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("pageCantBeOpen", false);

        return "lk/lkPage";
    }

    @PostMapping("saveFiles{id}")
    public String saveFiles(@PathVariable("id") int id,
                            @RequestParam("passportFile") MultipartFile passportFile,
                            @RequestParam("certificateFile") MultipartFile certificateFile,
                            @RequestParam("statimentFile") MultipartFile statimentFile,
                            @RequestParam("photoFile") MultipartFile photoFile,
                            @RequestParam("benefitFile") MultipartFile benefitFile,
                            @RequestParam("armyFile") MultipartFile armyFile,
                            @RequestParam("medicalFile") MultipartFile medicalFile,
                            Model model) {
        Enrollee enrollee = enrolleeService.getEnrolleeById(id);

        model.addAttribute("programEnrollees",
                programEnrolleeService.getAllProgramEnrolleeByEnrolleeAndExamResultAboveZero(enrollee));
        model.addAttribute("enrollee", enrollee);
        model.addAttribute("pageCantBeOpen", false);

        if (saveFile(id, "passport", passportFile))
            enrollee.setPassportNumber(id);

        if (saveFile(id, "certificate", certificateFile))
            enrollee.setCertificateNumber(id);

        if (saveFile(id, "statiment", statimentFile))
            enrollee.setStatimentNumber(id);

        if (saveFile(id, "photo", photoFile))
            enrollee.setPhotoNumber(id);

        if (saveFile(id, "benefit", benefitFile))
            enrollee.setBenefitNumber(id);

        if (saveFile(id, "army", armyFile))
            enrollee.setArmyNumber(id);

        if (saveFile(id, "medical", medicalFile))
            enrollee.setMedicalNumber(id);

        enrolleeService.saveChangedEnrollee(enrollee);

        return "lk/lkPage";
    }

    private boolean saveFile(int id, String type, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream("/Users/egor/Desktop/files/" + type + "/" + id));
                stream.write(bytes);
                stream.close();

                System.out.println("Success!!");


                return true;
            } catch (Exception e) {
                System.out.println("Не удалось 1!");

                return false;
            }
        } else {
            System.out.println("Не удалось 2!");

            return false;
        }
    }
}
