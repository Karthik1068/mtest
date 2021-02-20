package com.details.management.controller;

import com.details.management.dto.Department;
import com.details.management.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")

public class DepartmentController {
    DepartmentService departmentService;
     public DepartmentController(DepartmentService departmentService) {
         this.departmentService = departmentService;
     }


    /**
     * Method to insert Department Record
     * @param department
     * @return
     **/
    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Department insertDepartmentRecord(@RequestBody Department department) {
        return departmentService.insertDepartmentRecord(department);
    }


    /**
     * Method to update department Record
     * @param departmentId
     * @param department
     * @return
     */
    @PutMapping(value = "/{departmentId}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Department updateDepartmentRecord(@PathVariable("departmentId") int departmentId,
                                             @RequestBody @Validated Department department) {
        return departmentService.updateDepartmentRecord(departmentId, department);
    }

    /**
     * Method to delete department Record By department Id
     * @param departmentId
     * @return
     */

    @DeleteMapping(value = "/{departmentId}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> deleteDepartmentRecordById(@PathVariable int departmentId) {
        if (departmentId > 0) {
            departmentService.deleteDepartmentRecordById(departmentId);
            return new ResponseEntity<>("Record as been successfully deleted: " + departmentId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available for the requested id: " + departmentId,
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to get department Record By Id
     * @param departmentId
     * @return
     **/
    @GetMapping(value = "/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDepartmentRecordByDepartmentId(@PathVariable int departmentId) {
        Optional<Department> response = departmentService.getDepartmentRecordByDepartmentId(departmentId);
        if (response.isPresent()) {
            return new ResponseEntity<>(response.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available for the requested id: " + departmentId,
                    HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to get All department Records
     * @return
     **/
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAllDepartmentRecords() {
        List<Department> response = departmentService.getAllDepartmentRecords();
        if (response.size() > 0) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No records available in DB", HttpStatus.NOT_FOUND);
        }
    }
}
