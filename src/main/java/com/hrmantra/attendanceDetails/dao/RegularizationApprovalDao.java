package com.hrmantra.attendanceDetails.dao;

import com.hrmantra.attendanceDetails.model.RegularizationApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RegularizationApprovalDao extends JpaRepository<RegularizationApproval,Long> {
    @Query("SELECT ra FROM RegularizationApproval ra WHERE ra.empId = :empId AND ra.date BETWEEN :fromDate AND :toDate")
    List<RegularizationApproval> findBetweenDates(
            @Param("empId") Long empId,
            @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate
    );
}
