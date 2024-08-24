package com.example.student.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequestMapping("/enroll")
public class EnrollController {
    @GetMapping({"","/"})
    public String getAll() {
        System.out.println("------ EnrollController getAll ------");
        return "index";
    }
    

    @GetMapping("/{enroll-id}")
    public String getById(@PathVariable (name = "enroll-id") Integer enrollId) {
        System.out.println("--------- EnrollController GetById()---------");
        System.out.println("enroll-id: " + enrollId);
        return "index";

    }

    @GetMapping("/delete/{enroll-id}")
    public String getDeleteById(@PathVariable (name = "enroll-id") Integer enrollId) {
        System.out.println("----EnrollController getDelete()----");
        System.out.println("enroll ID: " + enrollId);
        return "index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(@RequestParam Map<String, String> param) 
    {
        System.out.println("---------postInsertAndUpdate()---------");
        System.out.println("enroll-id: " + param.get("enroll-id"));
        System.out.println("enroll-name: " + param.get("enroll-name"));
        return "index";
    }
    
}
