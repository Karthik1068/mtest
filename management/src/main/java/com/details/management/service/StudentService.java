package com.details.management.service;

import com.details.management.dto.Course;
import com.details.management.dto.Student;
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
     * @param student
     * @return
     **/
    public Student insertStudentRecord(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Method to update Student Record
     * @param studentId
     * @param student
     * @return
     **/
    public Student updateStudentRecord(int studentId, Student student) {
        Optional<Student> oldStudentDto = getStudentRecordByStudentId(studentId);
        if(oldStudentDto != null) {
            BeanUtils.copyProperties(student, oldStudentDto);
            return studentRepository.save(student);
        } else {
            return studentRepository.save(student);
        }
    }

    /**
     * Method to get Student Record By studentId
     * @param studentId
     * @return
     **/
    public Optional<Student> getStudentRecordByStudentId(int studentId) {
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
    public List<Student> getAllStudentRecords() {
        return (List<Student>) studentRepository.findAll();
    }

    /**
     * Method to get Course Records By studentId
     * @param studentId
     * @return
     **/
    public List<Course> getCourseRecordsByStudentId(int studentId) {
            return null;
    }

//    public List<CourseDto> getCourseDuration(int studentId) {
//        return studentRepository.getCourseDuration(studentId);
//    }
}