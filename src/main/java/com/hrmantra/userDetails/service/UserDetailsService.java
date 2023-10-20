package com.hrmantra.userDetails.service;

import com.hrmantra.userDetails.model.User;

import java.util.List;


public interface UserDetailsService {

    public User getUserByEmailAndPassword(String username, String password);

    public User getUserByEmail(String email);

    public User saveUserDetails(User user);

    public List<User> getAllUserDetails();

    public User updateUserDetails(User user);

}
