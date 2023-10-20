package com.hrmantra.attendanceDetails.dao;

import com.hrmantra.attendanceDetails.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Long> {

        public Employee findByEmailId(@Param("email") String email);

//        @Query("SELECT e.managerid FROM Employee e WHERE e.empid = :empId")
//        public Long getManagerEmpId(@Param("empId") Long empId);

        public  Employee findByEmpId(@Param("empId") Long empId);

}
