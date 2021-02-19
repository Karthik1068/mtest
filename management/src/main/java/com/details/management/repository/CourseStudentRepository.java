package com.details.management.repository;

import com.details.management.dto.CourseStudentDto;
import org.springframework.data.repository.CrudRepository;

public interface CourseStudentRepository extends CrudRepository<CourseStudentDto, Integer> {
}
