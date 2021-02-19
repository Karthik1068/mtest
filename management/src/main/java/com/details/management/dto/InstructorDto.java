package com.details.management.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "instructor")
public class InstructorDto {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int instructorId;
    private String departmentName;
    private String headedBy;
    private String firstName;
    private String lastName;
    private String phone;
    @CreationTimestamp
    private Date createdOn;
    private String createdBy;
    @UpdateTimestamp
    private Date updatedOn;
    private String updatedBy;
    @Transient
    private int activeStatus;

}