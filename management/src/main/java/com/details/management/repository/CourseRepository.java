package com.details.management.repository;

import com.details.management.dto.CourseDto;
import org.springframework.data.repository.CrudRepository;


public interface CourseRepository extends CrudRepository<CourseDto, Integer> {

}