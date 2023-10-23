package com.hrmantra.attendanceDetails.serviceImpl;

import com.hrmantra.attendanceDetails.dao.EmployeeDao;
import com.hrmantra.attendanceDetails.model.Employee;
import com.hrmantra.attendanceDetails.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public Employee getEmployeeByEmail(String emailId) {
        return employeeDao.findByEmailId(emailId);
    }

    @Override
    public Employee getEmployeeByEmpId(Long empId) {
        Optional<Employee> employee = employeeDao.findById(empId);
        return employee.orElse(null);
    }

    @Override
    public List<Employee> getAllEmployee() {
       return employeeDao.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee getManagerByEmpId(Long empId) {
        Employee managerEmpId = employeeDao.findByEmpId(empId);
        Optional<Employee> employeeOptional = employeeDao.findById(managerEmpId.getManagerId());
        return employeeOptional.orElse(null);
    }

    @Override
    public List<Long> getListOfAllReportee(Long managerId) {
        return employeeDao.findEmpIdsByManagerId(managerId);
    }

    @Override
    public List<Employee> getListOfAllReporteeDetails(Long managerId) {
        return employeeDao.findByManagerId(managerId);
    }
}
