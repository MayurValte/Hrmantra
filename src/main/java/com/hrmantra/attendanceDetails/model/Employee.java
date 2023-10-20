package com.hrmantra.attendanceDetails.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
        @Id
        @Column(name = "empid")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long empId;
        @Column(name = "empname")
        private String empName;
        @Column(name = "email")
        private String emailId;
        @Column(name = "position")
        private String position;
        @Column(name = "managerid")
        private Long managerId;

        @Column(name = "salary")
        private Long salary;

    public Employee(String empName, String emailId, String position, Long managerId,Long salary) {
        this.empName = empName;
        this.emailId = emailId;
        this.position = position;
        this.managerId = managerId;
        this.salary=salary;
    }

    public Employee() {
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", position='" + position + '\'' +
                ", managerId=" + managerId +
                ", salary=" + salary +
                '}';
    }
}
