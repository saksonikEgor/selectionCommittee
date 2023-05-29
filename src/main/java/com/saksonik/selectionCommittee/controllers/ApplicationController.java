package com.saksonik.selectionCommittee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    @GetMapping("/")
    public String getMainPage() {
        return "mainPage";
    }

}
