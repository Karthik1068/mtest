package com.details.management.service;

import com.details.management.dto.CourseDto;
import com.details.management.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Optional;

@Service

public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Method to insert Course Record
     * @param courseDto
     * @return
     */
    public CourseDto insertCourseRecord(CourseDto courseDto) {
        return courseRepository.save(courseDto);
    }

   /**
     * Method to update Course Record
     * @param courseId
     * @param courseDto
     * @return
     **/
    public CourseDto updateCourseRecord(int courseId, CourseDto courseDto) {
        Optional<CourseDto> oldCourseDto = getCourseRecordByCourseId(courseId);
        if(oldCourseDto != null) {
            BeanUtils.copyProperties(courseDto, oldCourseDto);
            return courseRepository.save(courseDto);
        } else {
            return courseRepository.save(courseDto);
        }
    }

    /**
     * Method to get Course Record By courseId
     * @param courseId
     * @return
     **/
    public Optional<CourseDto> getCourseRecordByCourseId(int courseId) {
        return courseRepository.findById(courseId);
    }

    /**
     * Method to delete Course Record By courseId
     * @param courseId
     * @return
     */
    public void deleteCourseRecordById(int courseId) {
        courseRepository.deleteById(courseId);
    }

    /**
     * Method to get All Course Records
     * @return
     **/
    public List<CourseDto> getAllCourseRecords() {
        return (List<CourseDto>) courseRepository.findAll();
    }
}