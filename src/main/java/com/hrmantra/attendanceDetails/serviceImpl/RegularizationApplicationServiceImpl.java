package com.hrmantra.attendanceDetails.serviceImpl;

import com.hrmantra.attendanceDetails.dao.EmployeeDao;
import com.hrmantra.attendanceDetails.dao.RegularizationApplicationDao;
import com.hrmantra.attendanceDetails.genericResponse.GenericResponse;
import com.hrmantra.attendanceDetails.model.Employee;
import com.hrmantra.attendanceDetails.model.RegularizationApplication;
import com.hrmantra.attendanceDetails.service.RegularizationApplicationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegularizationApplicationServiceImpl implements RegularizationApplicationService {

    @Autowired
    private RegularizationApplicationDao regularizationApplicationDao;


    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<RegularizationApplication> getAllRegularizationByManagerIdAndEmpId(Long managerId) {
        List<Long> empIds = employeeDao.findEmpIdsByManagerId(managerId);
        return regularizationApplicationDao.findByEmpIdIn(empIds);
    }

    @Override
    public RegularizationApplication saveRegularization(RegularizationApplication regularizationApplication) {
        return regularizationApplicationDao.save(regularizationApplication);
    }

    @Override
    public List<RegularizationApplication> getRegularizationByEmpId(Long empId) {
        return regularizationApplicationDao.findByEmpId(empId);
    }

    @Transactional
    @Override
    public Boolean deleteRegularizationByEmpIdAndDate(Long empId, Date date) {
        try {
            regularizationApplicationDao.deleteByEmpIdAndDate(empId,date);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public RegularizationApplication getRegularizationByEmpIdAndDate(Long empId, Date date) {
        return regularizationApplicationDao.findByEmpIdAndDate(empId, date);
    }
}
