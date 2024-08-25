package com.example.student.Controller;

import com.example.student.entity.FacultyEntity;
import com.example.student.entity.StudentEntity;
import com.example.student.service.FacultyService;
import com.example.student.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FacultyService facultyService;

    @GetMapping({"", "/"})
    public String getAll() {
        System.out.println("---------GetAll()---------");
        List<StudentEntity> students = studentService.getStudentEntities();
        System.out.println("--------- GetAll() Result ---------");
        System.out.println("Size: " + students.size());
        return "student/index";
    }

    @GetMapping("/{student-id}")
    public String getById(
        @PathVariable(name = "student-id") Integer studentId
    ) {
        System.out.println("---------GetById()---------");
        System.out.println("student-id: " + studentId);

        StudentEntity entity = studentService.getStudentById(studentId);
        System.out.println("--------- GetById() Result ---------");
        System.out.println("Student First Name: " + entity.getStudentFirstName());
        System.out.println("Student Last Name: " + entity.getStudentLastName());
        return "student/index";
    }

    @GetMapping("/delete/{student-id}")
    public String getDeleteById(
        @PathVariable(name = "student-id") Integer studentId
    ) {
        System.out.println("---------GetDeleteById()---------");
        System.out.println("student-id: " + studentId);

        studentService.deleteStudentById(studentId);
        System.out.println("--------- GetDeleteById() Result ---------");
        return "student/index";
    }

    @PostMapping("/")
    public String postInsertAndUpdate(
        @RequestParam Map<String, String> params
    ) {
        System.out.println("---------PostInsertAndUpdate()---------");
        System.out.println("student-id: " + params.get("student-id"));
        System.out.println("student-code: " + params.get("student-code"));
        System.out.println("student-fname: " + params.get("student-fname"));
        System.out.println("student-lname: " + params.get("student-lname"));

        System.out.println("faculty-id: " + params.get("faculty-id"));

        System.out.println("---------PostInsertAndUpdate() Result---------");
        Integer facultyId = Integer.parseInt(params.get("faculty-id"));
        FacultyEntity facultyEntity = facultyService.getFacultyEntityById(facultyId);
        System.out.println(facultyEntity.getFacultyId());
        
        StudentEntity entity = new StudentEntity();
        if (null != params.get("student-id")) {
                entity.setStudentId(Integer.parseInt(params.get("student-id")));
        }
        entity.setStudentCode(params.get("student-code"));
        entity.setStudentFirstName(params.get("student-fname"));
        entity.setStudentLastName(params.get("student-lname"));
        entity.setFaculty(facultyEntity);

        StudentEntity result = studentService.saveStudent(entity);
        System.out.println("Student ID: " + result.getStudentId());
        System.out.println("Student First Name: " + result.getStudentFirstName());
        System.out.println("Student Last Name: " + result.getStudentLastName());

            return "student/index";
    }
}
