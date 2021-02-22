package com.details.management.service;

import com.details.management.exception.DataResourceNotFoundException;
import com.details.management.model.Department;
import com.details.management.dao.DepartmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    public Department updateDepartment(Department department) {
        BeanUtils.copyProperties(department, department);
        return departmentRepository.save(department);
    }

    public Department getDepartmentByDepartmentName(String departmentName) throws DataResourceNotFoundException {
        return departmentRepository.findFirstByName(departmentName).orElseThrow(() -> new DataResourceNotFoundException("Requested department not found"));
    }

    public void deleteDepartmentByDepartmentName(String departmentName) {
        departmentRepository.deleteFirstByName(departmentName);
    }

    public List<Department> getAllDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }
}