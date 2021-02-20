package com.details.management.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    private String name;
    private String location;
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
}