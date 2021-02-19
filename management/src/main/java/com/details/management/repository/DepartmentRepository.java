package com.details.management.repository;

import com.details.management.dto.DepartmentDto;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<DepartmentDto, Integer> {
}
