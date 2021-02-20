package com.details.management.service;

import com.details.management.dto.Department;
import com.details.management.repository.DepartmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    public Department insertDepartmentRecord(Department department) {
        return departmentRepository.save(department);
    }


    /**
     * Method to update Instructor Record
     * @param departmentId
     * @param department
     * @return
     **/
    public Department updateDepartmentRecord(int departmentId, Department department) {
        Optional<Department> oldDepartmentDto = getDepartmentRecordByDepartmentId(departmentId);
        if(oldDepartmentDto != null) {
            BeanUtils.copyProperties(department, oldDepartmentDto);
            return departmentRepository.save(department);
        } else {
            return departmentRepository.save(department);
        }
    }

    /**
     * Method to get Department Record By departmentId
     * @param departmentId
     * @return
     **/
    public Optional<Department> getDepartmentRecordByDepartmentId(int departmentId) {
        return departmentRepository.findById(departmentId);
    }

    /**
     * Method to delete department Record By department Id
     * @param departmentId
     * @return
     */
    public void deleteDepartmentRecordById(int departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    /**
     * Method to get All Department Records
     * @return
     **/
    public List<Department> getAllDepartmentRecords() {
        return (List<Department>) departmentRepository.findAll();
    }

}