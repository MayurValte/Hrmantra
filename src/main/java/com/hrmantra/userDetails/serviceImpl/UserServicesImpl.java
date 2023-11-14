package com.hrmantra.userDetails.serviceImpl;

import com.hrmantra.attendanceDetails.dao.EmployeeDao;
import com.hrmantra.attendanceDetails.model.Employee;
import com.hrmantra.userDetails.dao.UserDetailsDao;
import com.hrmantra.userDetails.model.User;
import com.hrmantra.userDetails.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserDetailsDao userDetailsDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserByEmail(String email) {
        return userDetailsDao.findByEmail(email);
    }

    @Override
    public User saveUserDetails(User user) {
        Employee emp=new Employee(user.getFirstName()+" "+user.getLastName(),user.getEmail(),"Employee",1L,500000L);
        Employee savedEmp= employeeDao.save(emp);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_NORMAL");
        return userDetailsDao.save(user);
    }

    @Override
    public List<User> getAllUserDetails() {
        return userDetailsDao.findAll();
    }

    @Override
    public User updateUserDetails(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return userDetailsDao.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userDetailsDao.findByUsername(username);
    }

    @Override
    public User getUserByEmpId(Long empId) {
        return this.userDetailsDao.findById(empId).get();
    }

    @Override
    public User updateRole(User user) {
        return this.userDetailsDao.save(user);
    }


}
