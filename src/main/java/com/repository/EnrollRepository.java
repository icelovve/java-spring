package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.entity.EnrollEntity;

public interface EnrollRepository extends JpaRepository<EnrollEntity,Integer> {
    
}
