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
        return "enroll/index";
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
        return "enroll/index";
    }

    @GetMapping("/delete/{enrollId}")
    public String getDeleteById(@PathVariable(name = "enrollId") Integer enrollId) {
        System.out.println("--------- GetDeleteById() ---------");
        System.out.println("Enroll ID: " + enrollId);

        enrollService.deleteEnrollEntityById(enrollId);
        System.out.println("--------- GetDeleteById() Result ---------");
        return "enroll/index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(@RequestParam Map<String, String> param) {
        System.out.println("--------- PostInsertAndUpdate() ---------");
        System.out.println("enroll-id: " + param.get("enrollId-id"));
        System.out.println("course-id: " + param.get("course-id"));
        System.out.println("student-id: " + param.get("student-id"));

        System.out.println("--------- PostInsertAndUpdate() Reuslt ---------");
        Integer courseId = Integer.parseInt(param.get("course-id"));
        CourseEntity courseEntity = courseService.getCourseById(courseId);
        System.out.println("CourseId: " + courseEntity.getCourseId());

        Integer studentId = Integer.parseInt(param.get("student-id"));
        StudentEntity studentEntity = studentService.getStudentById(studentId);
        System.out.println("studentId: " + studentEntity.getStudentId());

        EnrollEntity entity = new EnrollEntity();
        if (null != param.get("enroll-id")) {
            entity.setEnrollId(Integer.parseInt(param.get("enroll-id")));
        }
        entity.setCourse(courseEntity);
        entity.setStudent(studentEntity);
        EnrollEntity result = enrollService.saveEnroll(entity);
        System.out.println("Enroll ID: " + result.getEnrollId());
        System.out.println("Course Name: " + result.getCourse().getCourseName());
        System.out.println("Student Code: " + result.getStudent().getStudentCode());

        return "enroll/index";
    }
}
