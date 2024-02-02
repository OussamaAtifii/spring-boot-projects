package com.microservice.student.services;

import com.microservice.student.entities.Student;

import java.util.List;

public interface StudentService {

    Iterable<Student> findAll();
    Student findById(Long id);

    Student save(Student student);

    List<Student> findByCourseId(Long courseId);
}
