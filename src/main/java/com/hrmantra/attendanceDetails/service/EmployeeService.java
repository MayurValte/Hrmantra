package com.hrmantra.attendanceDetails.service;

import com.hrmantra.attendanceDetails.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {

    public Employee getEmployeeByEmail(String emailId);
    public Employee getEmployeeByEmpId(Long empId);

    public List<Employee> getAllEmployee();

    public Employee updateEmployee(Employee employee);

    public Employee getManagerByEmpId(Long empId);

    public List<Long> getListOfAllReportee(Long managerId);

    public List<Employee> getListOfAllReporteeDetails(Long managerId);

}
