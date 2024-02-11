package com.microservice.course.services;

import com.microservice.course.entities.Course;
import com.microservice.course.http.response.StudentByCourseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<Course> findAll();

    Course findById(Long id);

    void save(Course course);

    StudentByCourseResponse findStudentsByCourse(Long courseId);
}
