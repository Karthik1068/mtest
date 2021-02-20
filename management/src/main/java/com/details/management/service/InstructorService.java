package com.details.management.service;


import com.details.management.dto.Instructor;
import com.details.management.dto.Student;
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
     * @param instructor
     * @return
     **/
    public Instructor insertInstructorRecord(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    /**
     * Method to update Instructor Record
     * @param instructorId
     * @param instructor
     * @return
     **/
    public Instructor updateInstructorRecord(int instructorId, Instructor instructor) {
        Optional<Instructor> oldInstructorDto = getInstructorRecordByInstructorId(instructorId);
        if(oldInstructorDto != null) {
            BeanUtils.copyProperties(instructor, oldInstructorDto);
            return instructorRepository.save(instructor);
        } else {
            return instructorRepository.save(instructor);
        }
    }

    /**
     * Method to get Instructor Record By instructorId
     * @param instructorId
     * @return
     **/
    public Optional<Instructor> getInstructorRecordByInstructorId(int instructorId) {
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
    public List<Instructor> getAllInstructorRecords() {
        return (List<Instructor>) instructorRepository.findAll();
    }

    /**
     * Method to get Student Records By instructorId
     * @param instructorId
     * @return
     **/
    public List<Student> getStudentRecordsByInstructorId(int instructorId) {
        return null;
    }
//    public List<CourseDto> getCourseRecordsByStudentId(int studentId) {
//        return studentRepository.getCourseRecordsByStudentId(studentId);
//    }
}