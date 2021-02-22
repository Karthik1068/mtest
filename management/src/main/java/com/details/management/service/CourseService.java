package com.details.management.service;

import com.details.management.exception.DataResourceNotFoundException;
import com.details.management.model.Course;
import com.details.management.dao.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
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

    public Course insertCourseRecord(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourseRecord(int courseId, Course course) {
            if(course.getCourseID() == 0 ) {
                course.setCourseID(courseId);
            }
            BeanUtils.copyProperties(course, course);
            return courseRepository.save(course);
    }

    public Course getCourseRecordByCourseId(int courseId) throws DataResourceNotFoundException {
        return courseRepository.findById(courseId).orElseThrow(() -> new DataResourceNotFoundException("Requested course not found"));
    }

    public void deleteCourseRecordById(int courseId) {
        courseRepository.deleteById(courseId);
    }

    public List<Course> getAllCourseRecords() {
        return (List<Course>) courseRepository.findAll();
    }
}