package com.details.management.service;

import com.details.management.dto.CourseStudentDto;
import com.details.management.dto.StudentDto;
import com.details.management.repository.CourseStudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CourseStudentService {
    @Autowired
    CourseStudentRepository courseStudentRepository;

    public CourseStudentService(CourseStudentRepository courseStudentRepository) {
        this.courseStudentRepository = courseStudentRepository;
    }

    /**
     * Method to insert Course Student Record
     * @param courseStudentDto
     * @return
     */
    public CourseStudentDto insertCourseStudentRecord(CourseStudentDto courseStudentDto) {
        return courseStudentRepository.save(courseStudentDto);
    }

    /**
     * Method to update Course Student Record
     * @param courseStudentId
     * @param courseStudentDto
     * @return
     **/
    public CourseStudentDto updateCourseStudentRecord(int courseStudentId, CourseStudentDto courseStudentDto) {
        Optional<CourseStudentDto> oldCourseStudentDto = getCourseStudentRecordByCourseStudentId(courseStudentId);
        if(oldCourseStudentDto != null) {
            BeanUtils.copyProperties(courseStudentDto, oldCourseStudentDto);
            return courseStudentRepository.save(courseStudentDto);
        } else {
            return courseStudentRepository.save(courseStudentDto);
        }
    }

    /**
     * Method to get Course Student Record By courseStudentId
     * @param courseStudentId
     * @return
     **/
    public Optional<CourseStudentDto> getCourseStudentRecordByCourseStudentId(int courseStudentId) {
        return courseStudentRepository.findById(courseStudentId);
    }

    /**
     * Method to delete Course Student Record By courseStudentId
     * @param courseStudentId
     * @return
     */
    public void deleteCourseStudentRecordById(int courseStudentId) {
        courseStudentRepository.deleteById(courseStudentId);
    }

    /**
     * Method to get All Course Student Records
     * @return
     **/
    public List<CourseStudentDto> getAllCourseStudentRecords() {
        return (List<CourseStudentDto>) courseStudentRepository.findAll();
    }

}