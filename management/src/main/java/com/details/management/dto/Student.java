package com.details.management.dto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private long studentID;
    private String firstName;
    private String lastName;
    private int phone;
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

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "studentID"),
            inverseJoinColumns = @JoinColumn(name = "courseID"))
    List<Course> courses;
}
