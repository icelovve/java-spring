package com.example.student.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping({"", "/"})
    public String getAll() {
        System.out.println("----StudentController getAll()----");
        return "index";
    }

    @GetMapping("/{student-id}")
    public String getById(@PathVariable(name = "student-id") Integer studentId) {
        System.out.println("----StudentController getById()----");
        System.out.println("Student ID: " + studentId);
        return "index";
    }

    @GetMapping("/delete/{student-id}")
    public String getDeleteById(@PathVariable(name = "student-id") Integer studentId) {
        System.out.println("----StudentController getDeleteById()----");
        System.out.println("Student ID: " + studentId);
        return "index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
        @RequestParam Map<String, String> params) {
            System.out.println("----StudentController postInsertAndUpdate()----");
            System.out.println("student-id: " + params.get("student-id"));
            System.out.println("student-name: " + params.get("student-name"));
            return "index";
        }
}
