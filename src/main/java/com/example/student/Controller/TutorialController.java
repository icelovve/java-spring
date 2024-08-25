package com.example.student.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tutorial")
public class TutorialController {
    
    @GetMapping("/")
    public String getTutorial(
        @RequestParam(name = "id", required = false, defaultValue = "0") Integer id
    ) {
        System.out.println("----getTutorial----");
        System.out.println("ID: " + id);
        return "templates";
    }

    @GetMapping("/{id}")
    public String getTutorialPath(
        @PathVariable("id") Integer id
    ) {
        System.out.println("----getTutorialPath----");
        System.out.println("ID: " + id);
        return "index";
    }

    @PostMapping("/")
    public String postMethodName(
        @RequestParam(name = "id", required = false, defaultValue = "0") Integer id,
        @RequestParam(name = "code", required = false, defaultValue = "") String code
    ) {
        System.out.println("----postMethodName----");
        System.out.println("ID: " + id);
        System.out.println("Code: " + code);
        return "index";
    }
}
