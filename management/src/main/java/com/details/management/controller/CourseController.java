package com.details.management.controller;

import com.details.management.dto.MessageResponse;
import com.details.management.model.Course;
import com.details.management.service.CourseService;
import io.swagger.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/courses")

public class CourseController {
    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Course> insertCourseRecord(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.insertCourseRecord(course), HttpStatus.OK);
    }

    @PutMapping(value = "/{courseId}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Course> updateCourseRecord(@PathVariable("courseId") int courseId,
                                     @RequestBody @Validated Course course) {
        return new ResponseEntity<>(courseService.updateCourseRecord(courseId, course), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{courseId}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> deleteCourseRecordById(@PathVariable int courseId) {
        if (courseId > 0) {
            courseService.deleteCourseRecordById(courseId);
            return new ResponseEntity<>(new MessageResponse("Record as been successfully deleted: " + courseId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("No records available for the requested id: " + courseId, "ERR-100", "Requested course ID not found"),
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getCourseRecordByStudentId(@PathVariable int courseId) throws Exception {
            return new ResponseEntity<>(courseService.getCourseRecordByCourseId(courseId), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<List<Course>> getAllCourseRecords() {
        List<Course> response = courseService.getAllCourseRecords();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
}
