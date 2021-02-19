package com.details.management.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name = "course")
public class CourseDto {

    @Id
    @GeneratedValue
    private int courseId;
    private String departmentName;
    private int instructorId;
    private int duration;
    private String name;
    @CreationTimestamp
    private Date createdOn;
    private String createdBy;
    @UpdateTimestamp
    private Date updatedOn;
    private String updatedBy;
    @Transient
    private int activeStatus;
    @ManyToOne
    @JoinColumn(name = "instructorId", insertable = false, updatable = false)
    private InstructorDto instructorDto;
    @ManyToOne
    @JoinColumn(name = "departmentId", insertable = false, updatable = false)
    private DepartmentDto departmentDto;

}