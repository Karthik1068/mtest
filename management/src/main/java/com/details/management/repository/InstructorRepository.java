package com.details.management.repository;

import com.details.management.dto.Instructor;
import com.details.management.dto.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InstructorRepository extends CrudRepository<Instructor, Integer> {

}
