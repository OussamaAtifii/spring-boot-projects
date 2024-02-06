package com.microservice.student.services;

import com.microservice.student.entities.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student findById(Long id);

    void save(Student student);

    List<Student> findByCourseId(Long courseId);
}
