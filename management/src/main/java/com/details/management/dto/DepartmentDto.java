package com.details.management.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "department")
public class DepartmentDto {
    @Id
    private String name;
    private String location;
    @CreationTimestamp
    private Date createdOn;
    private String createdBy;
    @UpdateTimestamp
    private Date updatedOn;
    private String updatedBy;
    @Transient
    private int activeStatus;


}