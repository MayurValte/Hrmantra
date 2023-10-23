package com.hrmantra.attendanceDetails.model;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "regularization")
public class RegularizationApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "empid")
    private Long empId;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
    @Column(name = "fromtime")
    private LocalTime fromTime;
    @Column(name = "totime")
    private LocalTime toTime;
    @Column(name = "comment")
    private String comment;

    public RegularizationApplication() {
    }

    public RegularizationApplication(Long empId, Date date, LocalTime fromTime, LocalTime toTime, String comment) {
        this.empId = empId;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "RegularizationApplication{" +
                "id=" + id +
                ", empId=" + empId +
                ", attendanceDate=" + date +
                ", fromTime=" + fromTime +
                ", toTime=" + toTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
