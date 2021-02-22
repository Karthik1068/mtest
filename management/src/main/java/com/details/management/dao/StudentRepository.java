package com.details.management.dao;

import com.details.management.model.Student;
import org.springframework.data.repository.CrudRepository;


public interface StudentRepository extends CrudRepository<Student, Integer> {

}