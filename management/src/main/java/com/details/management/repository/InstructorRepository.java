package com.details.management.repository;

import com.details.management.dto.InstructorDto;
import com.details.management.dto.StudentDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InstructorRepository extends CrudRepository<InstructorDto, Integer> {

    List<StudentDto> getStudentRecordsByInstructorId(int instructorId);
}
