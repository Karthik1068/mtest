package com.details.management.repository;

import com.details.management.dto.CourseDto;
import com.details.management.dto.StudentDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends CrudRepository<StudentDto, Integer> {

    List<CourseDto> getCourseRecordsByStudentId(int studentId);
//    List<CourseDto> getCourseDuration (int studentId);
}