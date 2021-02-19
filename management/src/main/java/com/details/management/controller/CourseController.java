package com.details.management.controller;

import com.details.management.dto.CourseDto;
import com.details.management.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/course")

public class CourseController {
    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Method to insert Course Record
     * @param courseDto
     * @return
     **/
    @PostMapping(value = "/insert", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CourseDto insertCourseRecord(@RequestBody CourseDto courseDto) {
        return courseService.insertCourseRecord(courseDto);
    }

    /**
       * Method to update Course Record
       * @param courseId
       * @param courseDto
       * @return
        */
    @PutMapping(value = "id/{courseId}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CourseDto updateCourseRecord(@PathVariable("courseId") int courseId,
                                          @RequestBody @Validated CourseDto courseDto) {
        return courseService.updateCourseRecord(courseId, courseDto);
    }

    /**
     * Method to delete course Record By courseId
     * @param courseId
     * @return
     */
    @DeleteMapping(value = "/{courseId}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> deleteCourseRecordById(@PathVariable int courseId) {
        if (courseId > 0) {
            courseService.deleteCourseRecordById(courseId);
            return new ResponseEntity<>("Record as been successfully deleted: " + courseId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available for the requested id: " + courseId,
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to get Course Record By Id
     * @param courseId
     * @return
     */
    @GetMapping(value = "/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCourseRecordByStudentId(@PathVariable int courseId) {
        Optional<CourseDto> response = courseService.getCourseRecordByCourseId(courseId);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available for the requested id: " + courseId,
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to get All Course Records
     * @return
     */
    @GetMapping(value = "/getAllRecords", produces = MediaType.APPLICATION_JSON_VALUE)

    public Object getAllCourseRecords() {
        List<CourseDto> response = courseService.getAllCourseRecords();
        if (response.size() > 0) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available in DB", HttpStatus.NOT_FOUND);
        }
    }
}
