package com.details.management.service;


import com.details.management.dto.InstructorDto;
import com.details.management.dto.StudentDto;
import com.details.management.repository.InstructorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    /**
     * Method to insert Instructor Record
     * @param instructorDto
     * @return
     **/
    public InstructorDto insertInstructorRecord(InstructorDto instructorDto) {
        return instructorRepository.save(instructorDto);
    }

    /**
     * Method to update Instructor Record
     * @param instructorId
     * @param instructorDto
     * @return
     **/
    public InstructorDto updateInstructorRecord(int instructorId, InstructorDto instructorDto) {
        Optional<InstructorDto> oldInstructorDto = getInstructorRecordByInstructorId(instructorId);
        if(oldInstructorDto != null) {
            BeanUtils.copyProperties(instructorDto, oldInstructorDto);
            return instructorRepository.save(instructorDto);
        } else {
            return instructorRepository.save(instructorDto);
        }
    }

    /**
     * Method to get Instructor Record By instructorId
     * @param instructorId
     * @return
     **/
    public Optional<InstructorDto> getInstructorRecordByInstructorId(int instructorId) {
        return instructorRepository.findById(instructorId);
    }

    /**
     * Method to delete Instructor Record By instructor Id
     * @param instructorId
     * @return
     */
    public void deleteInstructorRecordById(int instructorId) {
        instructorRepository.deleteById(instructorId);
    }

    /**
     * Method to get All Instructor Records
     * @return
     **/
    public List<InstructorDto> getAllInstructorRecords() {
        return (List<InstructorDto>) instructorRepository.findAll();
    }

    /**
     * Method to get Student Records By instructorId
     * @param instructorId
     * @return
     **/
    public List<StudentDto> getStudentRecordsByInstructorId(int instructorId) {
        return instructorRepository.getStudentRecordsByInstructorId(instructorId);
    }
//    public List<CourseDto> getCourseRecordsByStudentId(int studentId) {
//        return studentRepository.getCourseRecordsByStudentId(studentId);
//    }
}