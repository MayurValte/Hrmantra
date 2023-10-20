package com.hrmantra.userDetails.serviceImpl;

import com.hrmantra.attendanceDetails.dao.EmployeeDao;
import com.hrmantra.attendanceDetails.model.Employee;
import com.hrmantra.userDetails.dao.UserDetailsDao;
import com.hrmantra.userDetails.model.User;
import com.hrmantra.userDetails.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDetailsDao userDetailsDao;

    @Autowired
    private EmployeeDao employeeDao;
    @Override
    public User getUserByEmailAndPassword(String username, String password) {
        return userDetailsDao.findByEmailAndPassword(username, password);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDetailsDao.findByEmail(email);
    }

    @Override
    public User saveUserDetails(User user) {
        Employee emp=new Employee(user.getFirstName()+" "+user.getLastName(),user.getEmail(),"Employee",1L,500000L);
        Employee savedEmp= employeeDao.save(emp);
        if(savedEmp==null){
            return null;
        }
        return userDetailsDao.save(user);
    }

    @Override
    public List<User> getAllUserDetails() {
        return userDetailsDao.findAll();
    }

    @Override
    public User updateUserDetails(User user) {
        return userDetailsDao.save(user);
    }


}
