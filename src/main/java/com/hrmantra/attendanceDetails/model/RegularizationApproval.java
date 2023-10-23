package com.hrmantra.attendanceDetails.model;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "regularizationapproval")
public class RegularizationApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
    @Column(name = "empid")
    private Long empId;

    @Column(name = "fromtime")
    private LocalTime fromTime;
    @Column(name = "totime")
    private LocalTime toTime;

    @Column(name = "status")
    private String status;

    public RegularizationApproval() {
    }

    public RegularizationApproval(Date date, Long empId, LocalTime fromTime, LocalTime toTime, String status) {
        this.date = date;
        this.empId = empId;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RegularizationApproval{" +
                "id=" + id +
                ", date=" + date +
                ", empId=" + empId +
                ", fromTime=" + fromTime +
                ", toTime=" + toTime +
                ", status='" + status + '\'' +
                '}';
    }
}
