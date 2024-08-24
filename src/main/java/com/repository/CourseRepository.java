package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity,Integer>{
    
}
