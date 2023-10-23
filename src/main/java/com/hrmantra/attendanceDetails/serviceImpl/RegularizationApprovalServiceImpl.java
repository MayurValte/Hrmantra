package com.hrmantra.attendanceDetails.serviceImpl;

import com.hrmantra.attendanceDetails.constants.Status;
import com.hrmantra.attendanceDetails.dao.RegularizationApprovalDao;
import com.hrmantra.attendanceDetails.genericResponse.GenericResponse;
import com.hrmantra.attendanceDetails.model.RegularizationApplication;
import com.hrmantra.attendanceDetails.model.RegularizationApproval;
import com.hrmantra.attendanceDetails.service.MonthConverter;
import com.hrmantra.attendanceDetails.service.RegularizationApplicationService;
import com.hrmantra.attendanceDetails.service.RegularizationApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;
import java.util.List;

@Service
public class RegularizationApprovalServiceImpl implements RegularizationApprovalService {

    @Autowired
    private RegularizationApprovalDao approvalDao;

    @Autowired
    private RegularizationApplicationService applicationService;

    @Autowired
    private MonthConverter monthConverter;
    @Override
    public GenericResponse<Boolean> approveRegularization(Long empId, Date date, Boolean status) {
        RegularizationApplication r;
        try {
            r = applicationService.getRegularizationByEmpIdAndDate(empId, date);
        } catch (Exception e) {
            return new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"No application found with "+empId+" and "+date);
        }
        if (r == null) {
            return new GenericResponse<>(HttpStatus.NOT_FOUND.value(), "Requested regularization not found");
        }
        RegularizationApproval regularizationApproval;
        if(!status){
            regularizationApproval = new RegularizationApproval(r.getDate(), r.getEmpId(), LocalTime.of(0, 0, 0) , LocalTime.of(0, 0, 0), Status.REJECTED.name());
        }else {
            regularizationApproval = new RegularizationApproval(r.getDate(), r.getEmpId(), r.getFromTime(), r.getToTime(), Status.APPROVED.name());
        }
        RegularizationApproval saved;
        try {

            saved = approvalDao.save(regularizationApproval);
        }catch (Exception e){
            return new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error while approving regularization");
        }
        return new GenericResponse<>(HttpStatus.OK.value(), applicationService.deleteRegularizationByEmpIdAndDate(empId, date));
    }

    @Override
    public List<RegularizationApproval> getMonthlyTimeSheet(LocalDate from, LocalDate to,Long empId) {
        Date fromDate = Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date toDate = Date.from(to.atStartOfDay(ZoneId.systemDefault()).toInstant());
     return approvalDao.findBetweenDates(empId,fromDate, toDate);
    }

    @Override
    public List<LocalDate> getFromToDateFromMonth(String monthString) {
        String convertedMonth = monthConverter.convertShortToFull(monthString);
        Month month = Month.valueOf(convertedMonth.trim().toUpperCase());
        LocalDate from=LocalDate.of(2023,month,1);
        YearMonth yearMonth = YearMonth.of(2023, month);
        LocalDate to=LocalDate.of(2023,month, yearMonth.atEndOfMonth().getDayOfMonth());
        return List.of(from,to);
    }
}
