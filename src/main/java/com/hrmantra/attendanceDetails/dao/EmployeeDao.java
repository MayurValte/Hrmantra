package com.hrmantra.attendanceDetails.dao;

import com.hrmantra.attendanceDetails.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Long> {

        public Employee findByEmailId(@Param("email") String email);

        public  Employee findByEmpId(@Param("empId") Long empId);

        @Query("SELECT e.empId FROM Employee e WHERE e.managerId = :managerId")
        List<Long> findEmpIdsByManagerId(@Param("managerId") Long managerId);

        List<Employee> findByManagerId(Long ManagerId);

}
