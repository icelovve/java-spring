package com.example.student.Controller;

import com.example.student.entity.CourseEntity;
import com.example.student.entity.EnrollEntity;
import com.example.student.entity.StudentEntity;
import com.example.student.service.CourseService;
import com.example.student.service.EnrollService;
import com.example.student.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public String getAll(ModelMap model) {
        System.out.println("--------- GetAll() ---------");
        //List<EnrollEntity> enrollList = enrollService.getEnrollEntities();
        //System.out.println("--------- GetAll() Result ---------");
        //System.out.println("Size: " + enrollList.size());

        List <CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courses", courses);
        return "enroll/index";
    }
    @GetMapping("/{student-id}")
    public String getById(
            @PathVariable(name = "student=id") Integer studentId,
            ModelMap model) {
    
        StudentEntity student = studentService.getStudentById(studentId);
        model.addAttribute("student", student);
        
        List<EnrollEntity> enrolls = enrollService.getEnrollEntities();
         
        List<EnrollEntity> resultList = enrolls.stream()
        .filter(enroll -> enroll.getStudent().getStudentId() == studentId)
        .collect(Collectors.toList());
        model.addAttribute("enrolls", resultList);

        List<CourseEntity> courses = courseService.getCourseAll();
        model.addAttribute("courese",courses);
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
