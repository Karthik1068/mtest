package com.details.management.controller;

import com.details.management.dto.InstructorDto;
import com.details.management.dto.StudentDto;
import com.details.management.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/instructor")

public class InstructorController {
    InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    /**
     * Method to insert Instructor Record
     * @param instructorDto
     * @return
     **/
    @PostMapping(value = "/insert", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public InstructorDto insertInstructorRecord(@RequestBody InstructorDto instructorDto) {
        return instructorService.insertInstructorRecord(instructorDto);
    }

    /**
     * Method to update Instructor Record
     * @param instructorId
     * @param instructorDto
     * @return
     */
    @PutMapping(value = "id/{instructorId}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public InstructorDto updateInstructorRecord(@PathVariable("instructorId") int instructorId,
                                                @RequestBody @Validated InstructorDto instructorDto) {
        return instructorService.updateInstructorRecord(instructorId, instructorDto);
    }

    /**
     * Method to delete instructor Record By instructor Id
     * @param instructorId
     * @return
     */
    @DeleteMapping("/{instructorId}")
    public ResponseEntity<Object> deleteInstructorRecordById(@PathVariable int instructorId) {
        if (instructorId > 0) {
            instructorService.deleteInstructorRecordById(instructorId);
            return new ResponseEntity<>("Record as been successfully deleted: " + instructorId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available for the requested id: " + instructorId,
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to get instructor Record By Id
     * @param instructorId
     * @return
     **/
    @GetMapping(value = "/{instructorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getInstructorRecordByInstructorId(@PathVariable int instructorId) {
        Optional<InstructorDto> response = instructorService.getInstructorRecordByInstructorId(instructorId);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available for the requested id: " + instructorId,
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to get All instructor Records
     * @return
     **/
    @GetMapping(value = "/getAllRecords", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAllInstructorRecords() {
        List<InstructorDto> response = instructorService.getAllInstructorRecords();
        if (response.size() > 0) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available in DB", HttpStatus.NOT_FOUND);
        }
    }



    /**
     * get Student list by instructor id
     * @param instructorId
     * @return
     */

    @GetMapping(value = "/getStudentList/{instructorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStudentRecordsByInstructorId(@PathVariable("instructorId") int instructorId) {
        List<StudentDto> response = instructorService.getStudentRecordsByInstructorId(instructorId);
        if (response.size() > 0) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No student available for this instructor: " + instructorId,
                    HttpStatus.NOT_FOUND);
        }
    }
}