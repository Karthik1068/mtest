package com.details.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue
    private long courseID;
    private long duration;
    private String name;
    @JsonIgnore
    @CreationTimestamp
    private Date createdOn;
    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    @UpdateTimestamp
    private Date updatedOn;
    @JsonIgnore
    private String updatedBy;
    @JsonIgnore
    @Transient
    private boolean activeStatus;
    @ManyToOne
    @JoinColumn(name = "instructorID", insertable = false, updatable = false)
    private Instructor instructor;
    @ManyToOne
    @JoinColumn(name = "departmentName", insertable = false, updatable = false)
    private Department department;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    List<Student> students;

}