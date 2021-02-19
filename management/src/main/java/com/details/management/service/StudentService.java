package com.details.management.service;

import com.details.management.dto.CourseDto;
import com.details.management.dto.StudentDto;
import com.details.management.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Method to insert Student Record
     * @param studentDto
     * @return
     **/
    public StudentDto insertStudentRecord(StudentDto studentDto) {
        return studentRepository.save(studentDto);
    }

    /**
     * Method to update Student Record
     * @param studentId
     * @param studentDto
     * @return
     **/
    public StudentDto updateStudentRecord(int studentId, StudentDto studentDto) {
        Optional<StudentDto> oldStudentDto = getStudentRecordByStudentId(studentId);
        if(oldStudentDto != null) {
            BeanUtils.copyProperties(studentDto, oldStudentDto);
            return studentRepository.save(studentDto);
        } else {
            return studentRepository.save(studentDto);
        }
    }

    /**
     * Method to get Student Record By studentId
     * @param studentId
     * @return
     **/
    public Optional<StudentDto> getStudentRecordByStudentId(int studentId) {
        return studentRepository.findById(studentId);
    }

    /**
     * Method to delete Student Record By studentId
     * @param studentId
     * @return
     */
    public void deleteStudentRecordById(int studentId) {
        studentRepository.deleteById(studentId);
    }

    /**
     * Method to get All Student Records
     * @return
     **/
    public List<StudentDto> getAllStudentRecords() {
        return (List<StudentDto>) studentRepository.findAll();
    }

    /**
     * Method to get Course Records By studentId
     * @param studentId
     * @return
     **/
    public List<CourseDto> getCourseRecordsByStudentId(int studentId) {
            return studentRepository.getCourseRecordsByStudentId(studentId);
    }

//    public List<CourseDto> getCourseDuration(int studentId) {
//        return studentRepository.getCourseDuration(studentId);
//    }
}