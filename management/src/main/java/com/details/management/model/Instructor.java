package com.details.management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue
    private long instructorID;
    private String headedBy;
    private String firstName;
    private String lastName;
    private String phone;

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
    @JoinColumn(name = "departmentName", insertable = false, updatable = false)
    private Department department;

}