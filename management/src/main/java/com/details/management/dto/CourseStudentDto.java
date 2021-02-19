package com.details.management.dto;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "courseStudent")
public class CourseStudentDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     */
    @Id
    @GeneratedValue
    private int courseStudentId;
    private int courseId;
    private int studentId;
    @CreationTimestamp
    private Date createdOn;
    private String createdBy;
    @UpdateTimestamp
    private Date updatedOn;
    private String updatedBy;
    @Transient
    private int activeStatus;
    @ManyToOne
    @JoinColumn(name = "courseId", insertable = false, updatable = false)
    private CourseDto courseDto;
    @ManyToOne
    @JoinColumn(name = "studentId", insertable = false, updatable = false)
    private StudentDto studentDto;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCourseStudentId() {
        return courseStudentId;
    }

    public void setCourseStudentId(int courseStudentId) {
        this.courseStudentId = courseStudentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(int activeStatus) {
        this.activeStatus = activeStatus;
    }

    public CourseDto getCourseDto() {
        return courseDto;
    }

    public void setCourseDto(CourseDto courseDto) {
        this.courseDto = courseDto;
    }

    public StudentDto getStudentDto() {
        return studentDto;
    }

    public void setStudentDto(StudentDto studentDto) {
        this.studentDto = studentDto;
    }
}