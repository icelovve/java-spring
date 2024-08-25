package com.example.student.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.student.entity.CourseEntity;
import com.example.student.service.CourseService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping({"", "/"})
    public String getAll() {
        System.out.println("------ CourseController getAll ------");

        List<CourseEntity> courses = courseService.getCourseAll();
        System.out.println("------ CourseController getAll Result ------");
        System.out.println("Size: " + courses.size());
        return "course/index";
    }

    @GetMapping("/{course-id}")
    public String getById(@PathVariable("course-id") Integer courseId) {
        System.out.println("--------- GetById() ---------");
        System.out.println("course-id: " + courseId);

        CourseEntity entity = courseService.getCourseById(courseId);
        System.out.println("--------- GetById() Result ---------");
        System.out.println("Course Name: " + entity.getCourseName());
        return "course/index";
    }

    @GetMapping("/delete/{course-id}")
    public String getDeleteById(@PathVariable("course-id") Integer courseId) {
        System.out.println("---- CourseController getDelete() ----");
        System.out.println("Course ID: " + courseId);

        courseService.deleteCourseById(courseId);
        return "course/index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(@RequestParam Map<String, String> param) {
        System.out.println("--------- postInsertAndUpdate() ---------");
        System.out.println("course-id: " + param.get("course-id"));
        System.out.println("course-name: " + param.get("course-name"));

        System.out.println("--------- postInsertAndUpdate() Result ---------");
        CourseEntity entity = new CourseEntity();
        if (null != param.get("course-id")) {
            entity.setCourseId(Integer.parseInt(param.get("course-id")));
        }
        entity.setCourseName(param.get("course-name"));
        entity.setCourseDescription(param.get("course-description"));
        CourseEntity result = courseService.saveCourse(entity);
        System.out.println("Course ID: " + result.getCourseId());
        System.out.println("Course Name: " + result.getCourseName());
        return "course/index";
    }
}
