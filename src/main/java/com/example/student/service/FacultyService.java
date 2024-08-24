package com.example.student.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.entity.FacultyEntity;
import com.repository.FacultyRepository;

@Service
public class FacultyService {
    @Autowired
    private FacultyRepository facultyRepository;

    public List<FacultyEntity> getFacultyEntities(){
        return facultyRepository.findAll();
    }

    public FacultyEntity getFacultyEntityById(Integer fucultyId){
        Optional < FacultyEntity > faculty = facultyRepository.findById(fucultyId);
        if(faculty.isPresent()){
            return faculty.get();
        }
        return null;
        
    }

    public FacultyEntity saveFaculty(FacultyEntity facultyEntity){
        FacultyEntity faculty  = facultyRepository.save(facultyEntity);
        return faculty;
    }

    public void deleteFacultyEntityById(Integer fucultyId){
        facultyRepository.deleteById(fucultyId);;
    }
}


