package com.endava.demo.controller;

import com.endava.demo.entity.Intern;
import com.endava.demo.service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.endava.demo.entity.InternStreams.NET;

@Controller
public class InternController {

    @Autowired
    private InternService internService;

    @GetMapping("/newForm")
    public String secondView(Model model) {
        model.addAttribute("intern", new Intern("Serghei", 27, NET));
        return "newForm";
    }

    @PostMapping(value = "/newForm")
    public String fillForm(@ModelAttribute Intern intern){
        System.err.println("controller");
        internService.add(intern);
        return "redirect:/";
    }
    @GetMapping(value = "/delete/{id}")
    public String deleteIntern(@PathVariable int id)
    {

        internService.remove(id);
        return  "redirect:/";
    }

    @RequestMapping(value="/edit/{id}")
    public String edit(@PathVariable int id, Model m){
        Optional<Intern> intern=internService.getInternById(id);
        m.addAttribute("intern",intern);
        return "editForm";
    }

    @RequestMapping(value="/editsave",method = RequestMethod.POST)
    public String editsave(@ModelAttribute("intern") Intern intern){
        internService.update(intern);
        return "redirect:/";
    }
}
