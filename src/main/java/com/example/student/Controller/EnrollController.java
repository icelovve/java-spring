package com.example.student.Controller;

import com.example.student.entity.CourseEntity;
import com.example.student.entity.EnrollEntity;
import com.example.student.entity.StudentEntity;
import com.example.student.service.CourseService;
import com.example.student.service.EnrollService;
import com.example.student.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/enroll")
public class EnrollController {

    @Autowired
    private EnrollService enrollService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @GetMapping({"", "/"})
    public String getAll() {
        System.out.println("--------- GetAll() ---------");
        List<EnrollEntity> enrollList = enrollService.getEnrollEntities();
        System.out.println("--------- GetAll() Result ---------");
        System.out.println("Size: " + enrollList.size());
        return "index";
    }

    @GetMapping("/{enrollId}")
    public String getById(@PathVariable(name = "enrollId") Integer enrollId) {
        System.out.println("--------- GetById() ---------");
        System.out.println("Enroll ID: " + enrollId);

        EnrollEntity entity = enrollService.getEnrollEntityById(enrollId);
        if (entity == null) {
            System.out.println("Enroll not found");
            return "error"; // ใช้หน้า error แทน
        }

        System.out.println("--------- GetById() Result ---------");
        System.out.println("Enroll Details: " + entity);  
        return "index";
    }

    @GetMapping("/delete/{enrollId}")
    public String getDeleteById(@PathVariable(name = "enrollId") Integer enrollId) {
        System.out.println("--------- GetDeleteById() ---------");
        System.out.println("Enroll ID: " + enrollId);

        enrollService.deleteEnrollEntityById(enrollId);
        System.out.println("--------- GetDeleteById() Result ---------");
        return "index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(@RequestParam Map<String, String> param) {
        System.out.println("--------- PostInsertAndUpdate() ---------");
        String enrollIdStr = param.get("enroll-id");
        String courseIdStr = param.get("course-id");
        String studentIdStr = param.get("student-id");

        Integer courseId = null;
        Integer studentId = null;

        if (courseIdStr != null) {
            courseId = Integer.parseInt(courseIdStr);
        }
        if (studentIdStr != null) {
            studentId = Integer.parseInt(studentIdStr);
        }

        CourseEntity courseEntity = courseService.getCourseById(courseId);
        if (courseEntity == null) {
            System.out.println("Course not found");
            return "index";
        }
        System.out.println("Course Name: " + courseEntity.getCourseName());

        StudentEntity studentEntity = studentService.getStudentById(studentId);
        if (studentEntity == null) {
            System.out.println("Student not found");
            return "index";
        }
        System.out.println("Student Name: " + studentEntity.getStudentCode());

        EnrollEntity entity = new EnrollEntity();
        if (enrollIdStr != null) {
            entity.setEnrollId(Integer.parseInt(enrollIdStr));
        }

        entity.setCourse(courseEntity);
        entity.setStudent(studentEntity);
        EnrollEntity result = enrollService.saveEnroll(entity);
        System.out.println("Enroll ID: " + result.getEnrollId());
        System.out.println("Course Name: " + result.getCourse().getCourseName());
        System.out.println("Student Code: " + result.getStudent().getStudentCode());

        return "index";
    }
}
