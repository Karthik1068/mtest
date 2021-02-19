package com.details.management.controller;

import com.details.management.dto.CourseDto;
import com.details.management.dto.CourseStudentDto;
import com.details.management.service.CourseStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/courseStudent")

public class CourseStudentController {
    CourseStudentService courseStudentService;

    public CourseStudentController(CourseStudentService courseStudentService) {
        this.courseStudentService = courseStudentService;
    }

    /**
     * Method to insert Course Student Record
     * @param courseStudentDto
     * @return
     **/
    @PostMapping(value = "/insert", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CourseStudentDto insertCourseStudentRecord(@RequestBody CourseStudentDto courseStudentDto) {
        return courseStudentService.insertCourseStudentRecord(courseStudentDto);
    }

    /**
     * Method to update Course Record
     * @param courseStudentId
     * @param courseStudentDto
     * @return
     */
    @PutMapping(value = "id/{courseStudentId}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public CourseStudentDto updateCourseStudentRecord(@PathVariable("courseStudentId") int courseStudentId,
                                        @RequestBody @Validated CourseStudentDto courseStudentDto) {
        return courseStudentService.updateCourseStudentRecord(courseStudentId, courseStudentDto);
    }



    /**
     * Method to delete course Student Record By courseStudentId
     * @param courseStudentId
     * @return
     */
    @DeleteMapping(value = "/{courseStudentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteCourseStudentRecordById(@PathVariable int courseStudentId) {
        if (courseStudentId > 0) {
            courseStudentService.deleteCourseStudentRecordById(courseStudentId);
            return new ResponseEntity<>("Record as been successfully deleted: " + courseStudentId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available for the requested id: " + courseStudentId,
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to get Course Student Record By Id
     * @param courseStudentId
     * @return
     */
    @GetMapping(value = "/{courseStudentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCourseStudentRecordByStudentId(@PathVariable int courseStudentId) {
        Optional<CourseStudentDto> response = courseStudentService.getCourseStudentRecordByCourseStudentId(courseStudentId);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available for the requested id: " + courseStudentId,
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to get All Course Student Records
     * @return
     */
    @GetMapping(value = "/getAllRecords", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAllCourseStudentRecords() {
        List<CourseStudentDto> response = courseStudentService.getAllCourseStudentRecords();
        if (response.size() > 0) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available in DB", HttpStatus.NOT_FOUND);
        }
    }

}