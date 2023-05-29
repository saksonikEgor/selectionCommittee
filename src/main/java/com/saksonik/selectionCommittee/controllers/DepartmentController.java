package com.saksonik.selectionCommittee.controllers;

import com.saksonik.selectionCommittee.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("")
    public String getDepartments(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "departments/departmentsPage";
    }

    @GetMapping("/{id}")
    public String getProgramsOfDepartment(@PathVariable("id") int id, Model model) {
        model.addAttribute("department", departmentService.getById(id));
        model.addAttribute("programs", departmentService.getProgramsOfDepartment(id));
        return "departments/programsOfDepartmentPage";
    }

}
