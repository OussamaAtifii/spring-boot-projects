package com.microservice.course.controllers;

import com.microservice.course.entities.Course;
import com.microservice.course.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveStudent(@RequestBody Course course) {
        courseService.save(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<Iterable<Course>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/search-students/{courseId}")
    public ResponseEntity<?> findStudentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.findStudentsByCourse(courseId));
    }
}
