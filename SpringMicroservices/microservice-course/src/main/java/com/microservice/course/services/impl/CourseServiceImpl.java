package com.microservice.course.services.impl;

import com.microservice.course.client.StudentClient;
import com.microservice.course.entities.Course;
import com.microservice.course.entities.dto.StudentDto;
import com.microservice.course.http.response.StudentByCourseResponse;
import com.microservice.course.repositories.CourseRepository;
import com.microservice.course.services.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentClient studentClient;

    public CourseServiceImpl(CourseRepository courseRepository, StudentClient studentClient) {
        this.courseRepository = courseRepository;
        this.studentClient = studentClient;
    }

    @Override
    public List<Course> findAll() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Course course) {
        courseRepository.save(course);
    }

    @Override
    public StudentByCourseResponse findStudentsByCourse(Long courseId) {
        // Consultar el curso
        Course course = courseRepository.findById(courseId).orElse(new Course());

        // Consultar los estudiantes del curso
        List<StudentDto> students = studentClient.findAllStudentsByCourse(courseId);

        return StudentByCourseResponse.builder()
                .courseName(course.getName())
                .teacher(course.getTeacher())
                .students(students)
                .build();
    }
}
