package com.details.management.controller;

import com.details.management.dto.MessageResponse;
import com.details.management.exception.DataResourceNotFoundException;
import com.details.management.model.Student;
import com.details.management.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/academy/students")
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.OK);
    }

    @PutMapping(value = "/{studentId}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") int studentId,
                                       @RequestBody @Validated Student student) {
        return new ResponseEntity<>(studentService.updateStudent(studentId, student), HttpStatus.OK);
    }

    @PostMapping(value = "/{studentId}/courses/{courseId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MessageResponse> addCourseToStudent(@PathVariable("studentId") int studentId,
                                                      @PathVariable("courseId") int courseId) throws Exception {
        Student student = studentService.addCourse(studentId, courseId);
        return new ResponseEntity<>(new MessageResponse("Course :"+courseId+" has been added to Student: "+studentId+" successfully"), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{studentId}/courses/{courseId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MessageResponse> removeCourseFromStudent(@PathVariable("studentId") int studentId,
                                                              @PathVariable("courseId") int courseId) throws Exception {
        Student student = studentService.removeCourse(studentId, courseId);
        return new ResponseEntity<>(new MessageResponse("Course :"+courseId+" has been added to Student: "+studentId+" successfully"), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{studentId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MessageResponse> deleteStudent(@PathVariable int studentId) {
            studentService.deleteStudentById(studentId);
            return new ResponseEntity<>(new MessageResponse(studentId + " - Student removed successfully"), HttpStatus.OK);
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable int studentId) throws Exception {
            return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAllStudentRecords() {
            return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping(value = "/instructor/{instructorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAllStudentsByInstructor(@PathVariable int instructorId) {
        return new ResponseEntity<>(studentService.getAllStudentsByInstructorId(instructorId), HttpStatus.OK);
    }


    @GetMapping(value = "/{studentId}/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCoursesForStudent(@PathVariable("studentId") int studentId) throws DataResourceNotFoundException {
            return new ResponseEntity<>(studentService.getCourseForStudent(studentId), HttpStatus.OK);
    }

    @GetMapping(value = "/{studentId}/courses/duration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageResponse> getTotalCourseDurationForStudent(@PathVariable("studentId") int studentId) throws DataResourceNotFoundException {
        return new ResponseEntity<>(new MessageResponse("Total Course Duration = " + studentService.getTotalCourseDurationForStudent(studentId)), HttpStatus.OK);
    }
}
