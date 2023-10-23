package com.hrmantra.attendanceDetails.controller;

import com.hrmantra.attendanceDetails.model.RegularizationApplication;
import com.hrmantra.attendanceDetails.service.RegularizationApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/regularize")
public class RegularizationApplicationController {
    @Autowired
    private RegularizationApplicationService regularizationApplicationService;
    @GetMapping("/getRegularizationForManager")
    public ResponseEntity<List<RegularizationApplication>> getRegularizationForManager(@RequestParam("managerId") Long managerId){
        List<RegularizationApplication> regularizationByManagerIdAndEmpId = regularizationApplicationService.getAllRegularizationByManagerIdAndEmpId(managerId);
        return new ResponseEntity<>(regularizationByManagerIdAndEmpId, HttpStatus.OK);
    }

    @PostMapping("/saveRegularization")
    public ResponseEntity<RegularizationApplication> saveRegularization(@RequestBody RegularizationApplication regularizationApplication){
        RegularizationApplication savedRegularization = regularizationApplicationService.saveRegularization(regularizationApplication);
        return new ResponseEntity<>(savedRegularization, HttpStatus.OK);
    }

    @GetMapping("/getRegularization")
    public ResponseEntity<List<RegularizationApplication>> getRegularizationByEmpId(@RequestParam("empId") Long empId){
        List<RegularizationApplication> regularizationByEmpId = regularizationApplicationService.getRegularizationByEmpId(empId);
        return new ResponseEntity<>(regularizationByEmpId,HttpStatus.OK);
    }
    @DeleteMapping("/deleteRegularization")
    public ResponseEntity<Boolean> deleteRegularizationByEmpIdAndDate(@RequestParam("empId") Long empId,@RequestParam("date") Date date){
        Boolean deleted = regularizationApplicationService.deleteRegularizationByEmpIdAndDate(empId, date);
        if(deleted){
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
