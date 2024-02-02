package com.microservice.student.repositories;

import com.microservice.student.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRespository extends CrudRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.courseId = :courseId")
    List<Student> findAllAllByCourseId(Long courseId);

    // List<Student> findAllByCourseId(Long courseId);
}
