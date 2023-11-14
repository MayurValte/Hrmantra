package com.hrmantra.userDetails.service;

import com.hrmantra.userDetails.model.User;

import java.util.List;


public interface UserServices {

    public User getUserByEmail(String email);

    public User saveUserDetails(User user);

    public List<User> getAllUserDetails();

    public User updateUserDetails(User user);

    public User getUserByUsername(String username);


    User getUserByEmpId(Long empId);

    User updateRole(User user);
}
