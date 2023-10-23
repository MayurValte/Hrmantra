package com.hrmantra.attendanceDetails.controller;

import com.hrmantra.attendanceDetails.genericResponse.GenericResponse;
import com.hrmantra.attendanceDetails.model.RegularizationApplication;
import com.hrmantra.attendanceDetails.model.RegularizationApproval;
import com.hrmantra.attendanceDetails.service.RegularizationApplicationService;
import com.hrmantra.attendanceDetails.service.RegularizationApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("regularize")
public class RegularizationApprovalController {

    @Autowired
    private RegularizationApprovalService regularizationApprovalService;


    @GetMapping("/approveAttendance")
    public ResponseEntity<Boolean> approveRegularizationApplication(@RequestParam("empId") Long empId, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam("status") Boolean status){
            GenericResponse<Boolean> response = regularizationApprovalService.approveRegularization(empId, date, status);
        boolean b = response.getStatus() != HttpStatus.INTERNAL_SERVER_ERROR.value() && response.getStatus() != HttpStatus.NOT_FOUND.value();
        return new ResponseEntity<>(b, HttpStatusCode.valueOf(response.getStatus()));
    }

    @GetMapping("/monthlyTimeSheet")
    public ResponseEntity<GenericResponse<List<RegularizationApproval>>> getMonthlyTimeSheet(@RequestParam("month") String month, @RequestParam("empId") Long empId){
        List<LocalDate> fromToDateFromMonth;
        try {
            fromToDateFromMonth  = regularizationApprovalService.getFromToDateFromMonth(month.trim());
        }catch (Exception e){
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Exception while making to and from date "+e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<RegularizationApproval> timeSheet = regularizationApprovalService.getMonthlyTimeSheet(fromToDateFromMonth.get(0), fromToDateFromMonth.get(1), empId);
        if(timeSheet==null || timeSheet.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NOT_FOUND.value(),"Nothing found in the month of "+month),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(),"Successfully fetched result",timeSheet),HttpStatus.OK);
    }
}
