package com.details.management.controller;

import com.details.management.dto.MessageResponse;
import com.details.management.exception.DataResourceNotFoundException;
import com.details.management.model.Course;
import com.details.management.model.Department;
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


    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.updateDepartment(department), HttpStatus.OK);
    }

    @PutMapping(value = "/{departmentName}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Department> updateDepartment(@PathVariable("departmentName") int departmentId,
                                                     @RequestBody @Validated Department department) {
        return new ResponseEntity<>(departmentService.updateDepartment(department), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{departmentId}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MessageResponse> deleteDepartmentById(@PathVariable String departmentName) {
        departmentService.deleteDepartmentByDepartmentName(departmentName);
        return new ResponseEntity<>(new MessageResponse("Department has been deleted successfully : " + departmentName), HttpStatus.OK);
    }

    @GetMapping(value = "/{departmentName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> getDepartmentsByName(@PathVariable String departmentName) throws Exception {
        return new ResponseEntity<>(departmentService.getDepartmentByDepartmentName(departmentName), HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> response = departmentService.getAllDepartments();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
