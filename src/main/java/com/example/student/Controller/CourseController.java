package com.example.student.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    @GetMapping({"","/"})
    public String getAll() {
        System.out.println("------ CourseController getAll ------");
        return "index";
    }
    

    @GetMapping("/{course-id}")
    public String getById(@PathVariable (name = "course-id") Integer courseId) {
        System.out.println("---------GetById()---------");
        System.out.println("course-id: " + courseId);
        return "index";

    }

    @GetMapping("/delete/{course-id}")
    public String getDeleteById(@PathVariable (name = "course-id") Integer courseId) {
        System.out.println("----StudentController getDelete()----");
        System.out.println("Course ID: " + courseId);
        return "index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(@RequestParam Map<String, String> param) 
    {
        System.out.println("---------postInsertAndUpdate()---------");
        System.out.println("course-id: " + param.get("course-id"));
        System.out.println("course-name: " + param.get("course-name"));
        return "index";
    }
    
}
