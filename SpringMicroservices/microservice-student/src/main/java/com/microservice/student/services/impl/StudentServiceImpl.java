package com.microservice.student.services.impl;

import com.microservice.student.entities.Student;
import com.microservice.student.repositories.StudentRespository;
import com.microservice.student.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRespository studentRespository;

    public StudentServiceImpl(StudentRespository studentRespository) {
        this.studentRespository = studentRespository;
    }

    @Override
    public Iterable<Student> findAll() {
        return studentRespository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRespository.findById(id).orElse(null);
    }

    @Override
    public Student save(Student student) {
    return studentRespository.save(student);
    }

    @Override
    public List<Student> findByCourseId(Long courseId) {
        return studentRespository.findAllAllByCourseId(courseId);
    }
}
