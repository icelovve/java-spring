package com.example.student.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.student.entity.FacultyEntity;
import com.example.student.entity.StudentEntity;
import com.example.student.service.FacultyService;
import com.example.student.service.StudentService;

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
        System.out.println("----StudentController getAll()----");

        List<StudentEntity> students = studentService.getStudentEntities();
        System.out.println("----StudentController getAll() Result----");
        System.out.println("Size : " + students.size());
        return "index";
    }

    @GetMapping("/{student-id}")
    public String getById(@PathVariable(name = "student-id") Integer studentId) {
        System.out.println("----StudentController getById()----");
        System.out.println("Student ID: " + studentId);

        StudentEntity entity = studentService.getStudentById(studentId);
        System.out.println("----StudentController getById() Result----");
        System.out.println("Student First Name: " + entity.getStudentFirstName());
        System.out.println("Student Last Name: " + entity.getStudentLastName());
        return "index";
    }

    @GetMapping("/delete/{student-id}")
    public String getDeleteById(@PathVariable(name = "student-id") Integer studentId) {
        System.out.println("----StudentController getDeleteById()----");
        System.out.println("Student ID: " + studentId);

        studentService.deleteStudentById(studentId);
        System.out.println("----StudentController getDeleteById() Result----");
        return "index";
    }

    @PostMapping("/")
public String postInsertAndUpdate(@RequestParam Map<String, String> params) {
    System.out.println("----StudentController postInsertAndUpdate()----");
    System.out.println("student-id: " + params.get("student-id"));
    System.out.println("student-code: " + params.get("student-code"));
    System.out.println("student-Fname: " + params.get("student-fname"));
    System.out.println("student-Lname: " + params.get("student-lname"));

    System.out.println("----StudentController postInsertAndUpdate() Result----");

    Integer facultyId = null;
    if (params.get("faculty-id") != null && !params.get("faculty-id").isEmpty()) {
        try {
            facultyId = Integer.parseInt(params.get("faculty-id"));
        } catch (NumberFormatException e) {
            System.out.println("Invalid faculty-id format");
        }
    }

    FacultyEntity facultyEntity = null;
    if (facultyId != null) {
        facultyEntity = facultyService.getFacultyEntityById(facultyId);
        if (facultyEntity == null) {
            System.out.println("FacultyEntity not found for ID: " + facultyId);
        } else {
            System.out.println("Faculty ID: " + facultyEntity.getFacultyId());
        }
    }

    StudentEntity entity = new StudentEntity();
    if (params.get("student-id") != null && !params.get("student-id").isEmpty()) {
        try {
            entity.setStudentId(Integer.parseInt(params.get("student-id")));
        } catch (NumberFormatException e) {
            System.out.println("Invalid student-id format");
        }
    }
    entity.setStudentCode(params.get("student-code"));
    entity.setStudentFirstName(params.get("student-fname"));
    entity.setStudentLastName(params.get("student-lname"));
    entity.setFaculty(facultyEntity);

    StudentEntity result = studentService.saveStudent(entity);
    System.out.println("Student ID: " + result.getStudentId());
    System.out.println("Student Code: " + result.getStudentCode());
    System.out.println("Student First Name: " + result.getStudentFirstName());
    System.out.println("Student Last Name: " + result.getStudentLastName());

    return "index";
}

}
