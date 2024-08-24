package com.example.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.entity.EnrollEntity;
import com.repository.EnrollRepository;

@Service
public class EnrollService {
    @Autowired
    private EnrollRepository enrollRepository;

    public List<EnrollEntity> getEnrollEntities() {
        return enrollRepository.findAll();
    }

    public EnrollEntity getEnrollEntityById(Integer entityById) {
        Optional<EnrollEntity> enroll = enrollRepository.findById(entityById);
        if (enroll.isPresent()) {
            return enroll.get();
        }
        return null;
    }

    public void deleteEnrollEntityById(Integer entityById) {
        enrollRepository.deleteById(entityById);
    }
}
