package com.hrmantra.attendanceDetails.dao;

import com.hrmantra.attendanceDetails.model.RegularizationApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RegularizationApplicationDao extends JpaRepository<RegularizationApplication,Long> {

    public List<RegularizationApplication> findByEmpId(@Param("empId") Long empId);

    List<RegularizationApplication> findByEmpIdIn(List<Long> empIds);

    @Query("SELECT r FROM RegularizationApplication r WHERE r.empId = :empId AND DATE(r.date) = :targetDate")
    RegularizationApplication findByEmpIdAndDate(@Param("empId") Long empId, @Param("targetDate") Date targetDate);

    void deleteByEmpIdAndDate(@Param("empId") Long empId, @Param("date") Date date);
}
