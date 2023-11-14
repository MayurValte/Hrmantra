package com.hrmantra.attendanceDetails.controller;

import com.hrmantra.attendanceDetails.model.Employee;
import com.hrmantra.attendanceDetails.service.EmployeeService;
import com.hrmantra.userDetails.model.User;
import com.hrmantra.userDetails.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hr")
public class HrManagement {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAllEmp")
    public ResponseEntity<List<Employee>>getAllUser(){
        List<Employee> employeeList = employeeService.getAllEmployee();
        if(employeeList==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @PutMapping("/updateEmployeeDetails")
    public ResponseEntity<String> updateEmployeesManager(@RequestBody Employee employee){
        Employee employeeByEmail = employeeService.getEmployeeByEmail(employee.getEmailId());
        if(employeeByEmail==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with email id "+employee.getEmailId());
        }
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        if (updatedEmployee==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Couldn't update employee , Something went wrong");
        }
        return ResponseEntity.ok("Successfully assigned Manager and Position to employee");

    }

    @GetMapping("/getManagerByEmpId")
    public ResponseEntity<Employee> getManagerByEmpId(@RequestParam(name = "empId") Long empId){
        if(empId==1){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        Employee managerByEmpId = employeeService.getManagerByEmpId(empId);
        if(managerByEmpId==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(managerByEmpId,HttpStatus.OK);

    }
}
