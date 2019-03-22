package com.endava.demo.controller;

import com.endava.demo.service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private InternService internService;

    @GetMapping("/")
    public String view(Model model){
        model.addAttribute("interns", internService.getAllInterns());
        return "index";
    }


}
