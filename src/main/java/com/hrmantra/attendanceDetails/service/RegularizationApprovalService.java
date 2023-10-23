package com.hrmantra.attendanceDetails.service;

import com.hrmantra.attendanceDetails.genericResponse.GenericResponse;
import com.hrmantra.attendanceDetails.model.RegularizationApplication;
import com.hrmantra.attendanceDetails.model.RegularizationApproval;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RegularizationApprovalService {

    public GenericResponse<Boolean> approveRegularization(Long empId, Date date, Boolean status);

    public List<RegularizationApproval> getMonthlyTimeSheet(LocalDate from,LocalDate to,Long empId);

    public List<LocalDate> getFromToDateFromMonth(String month);
}
