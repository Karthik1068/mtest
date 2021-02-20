package com.details.management.service;

import com.details.management.dto.Course;
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
     * @param course
     * @return
     */
    public Course insertCourseRecord(Course course) {
        return courseRepository.save(course);
    }

   /**
     * Method to update Course Record
     * @param courseId
     * @param course
     * @return
     **/
    public Course updateCourseRecord(int courseId, Course course) {
        Optional<Course> oldCourseDto = getCourseRecordByCourseId(courseId);
        if(oldCourseDto != null) {
            BeanUtils.copyProperties(course, oldCourseDto);
            return courseRepository.save(course);
        } else {
            return courseRepository.save(course);
        }
    }

    /**
     * Method to get Course Record By courseId
     * @param courseId
     * @return
     **/
    public Optional<Course> getCourseRecordByCourseId(int courseId) {
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
    public List<Course> getAllCourseRecords() {
        return (List<Course>) courseRepository.findAll();
    }
}