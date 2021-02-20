package com.details.management.repository;

import com.details.management.dto.Course;
import com.details.management.dto.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface StudentRepository extends CrudRepository<Student, Integer> {

}