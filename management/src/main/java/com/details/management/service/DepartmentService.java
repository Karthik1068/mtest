package com.details.management.service;

import com.details.management.dto.DepartmentDto;
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
    public DepartmentDto insertDepartmentRecord(DepartmentDto departmentDto) {
        return departmentRepository.save(departmentDto);
    }


    /**
     * Method to update Instructor Record
     * @param departmentId
     * @param departmentDto
     * @return
     **/
    public DepartmentDto updateDepartmentRecord(int departmentId, DepartmentDto departmentDto) {
        Optional<DepartmentDto> oldDepartmentDto = getDepartmentRecordByDepartmentId(departmentId);
        if(oldDepartmentDto != null) {
            BeanUtils.copyProperties(departmentDto, oldDepartmentDto);
            return departmentRepository.save(departmentDto);
        } else {
            return departmentRepository.save(departmentDto);
        }
    }

    /**
     * Method to get Department Record By departmentId
     * @param departmentId
     * @return
     **/
    public Optional<DepartmentDto> getDepartmentRecordByDepartmentId(int departmentId) {
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
    public List<DepartmentDto> getAllDepartmentRecords() {
        return (List<DepartmentDto>) departmentRepository.findAll();
    }

}