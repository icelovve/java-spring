package com.example.student.Controller;

import com.example.student.entity.FacultyEntity;
import com.example.student.service.FacultyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping({ "", "/" })
    public String getAll(ModelMap model) {
        System.out.println("---------GetAll()---------");
        // List<FacultyEntity> facultyList = facultyService.getFacultyEntities();
        // System.out.println("--------- GetAll() Result ---------");
        // System.out.println("Size: " + facultyList.size());

        List<FacultyEntity> faculties = facultyService.getFacultyEntities();
        model.addAttribute("faculties", faculties);

        return "faculty/index";
    }

    @GetMapping("/{faculty-id}")
    public String getById(
            ModelMap model,
            @PathVariable(name = "faculty-id") Integer facultyId) {
        //System.out.println("---------GetById()---------");
        //System.out.println("faculty-id: " + facultyId);

        FacultyEntity entity = facultyService.getFacultyEntityById(facultyId);
        //System.out.println("--------- getById() Result ---------");
        //System.out.println("Faculty Name: " + entity.getFacultyName());

        model.addAttribute("faculty", entity);

        List<FacultyEntity> faculties = facultyService.getFacultyEntities();
        model.addAttribute("faculties", faculties);
        return "faculty/index";
    }

    @GetMapping("/delete/{faculty-id}")
    public String getDeleteById(
            ModelMap model,
            @PathVariable(name = "faculty-id") Integer facultyId) {
        // System.out.println("---------getDeleteById()---------");
        // System.out.println("faculty-id: " + facultyId);

        // System.out.println("--------- getDeleteById() Result ---------");
        facultyService.deleteFacultyEntityById(facultyId);

        List<FacultyEntity> faculty = facultyService.getFacultyEntities();
        model.addAttribute("faculties", faculty);

        return "faculty/index";
    }

    @PostMapping({"","/"})
    public String postInsertAndUpdate(
            ModelMap model,
            @RequestParam Map<String, String> param) {
        // System.out.println("---------postInsertAndUpdate()---------");
        // System.out.println("faculty-id: " + param.get("faculty-id"));
        // System.out.println("faculty-name: " + param.get("faculty-name"));

        // System.out.println("--------- postInsertAndUpdate() Result ---------");
        FacultyEntity entity = new FacultyEntity();
        if (param.get("faculty-id") != null) {
            entity.setFacultyId(Integer.parseInt(param.get("faculty-id")));
        }
        entity.setFacultyName(param.get("faculty-name"));
        FacultyEntity result = facultyService.saveFaculty(entity);
        // System.out.println("Faculty ID: " + result.getFacultyId());
        // System.out.println("Faculty Name: " + result.getFacultyName());

        List<FacultyEntity> faculty = facultyService.getFacultyEntities();
        model.addAttribute("faculties", faculty);

        return "faculty/index";
    }
}