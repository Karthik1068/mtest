package com.details.management.controller;

import com.details.management.model.Course;
import com.details.management.model.Student;
import com.details.management.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Student insertStudentRecord(@RequestBody Student student) {
        return studentService.insertStudentRecord(student);
    }

    @PutMapping(value = "/{studentId}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Student updateStudentRecord(@PathVariable("studentId") int studentId,
                                       @RequestBody @Validated Student student) {
        return studentService.updateStudentRecord(studentId, student);
    }

    @DeleteMapping(value = "/{studentId}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> deleteStudentRecordById(@PathVariable int studentId) {
        if (studentId > 0) {
            studentService.deleteStudentRecordById(studentId);
            return new ResponseEntity<>("Record as been successfully deleted: " + studentId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available for the requested id: " + studentId,
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Object> getStudentRecordByStudentId(@PathVariable int studentId) {
        Optional<Student> response = studentService.getStudentRecordByStudentId(studentId);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available for the requested id: " + studentId,
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAllStudentRecords() {
        List<Student> response = studentService.getAllStudentRecords();
        if (response.size() > 0) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available in DB", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(value = "/{studentId}/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCourseRecordsByStudentId(@PathVariable("studentId") int studentId) {
        List<Course> response = studentService.getCourseRecordsByStudentId(studentId);
        if (response.size() > 0) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No courses available for this student: " + studentId, HttpStatus.NOT_FOUND);
        }
    }
}
