package com.hrmantra.attendanceDetails.service;

import com.hrmantra.attendanceDetails.model.RegularizationApplication;

import java.util.Date;
import java.util.List;

public interface RegularizationApplicationService {

    public List<RegularizationApplication> getAllRegularizationByManagerIdAndEmpId(Long managerId);

    public RegularizationApplication saveRegularization(RegularizationApplication regularizationApplication);

    public List<RegularizationApplication> getRegularizationByEmpId(Long empId);

    public Boolean deleteRegularizationByEmpIdAndDate(Long empId, Date date);

    public RegularizationApplication getRegularizationByEmpIdAndDate(Long empId,Date date);
}
