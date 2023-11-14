package com.hrmantra.userDetails.serviceImpl;

import com.hrmantra.userDetails.dao.UserDetailsDao;
import com.hrmantra.userDetails.model.CustomUserServices;
import com.hrmantra.userDetails.model.User;
import com.hrmantra.userDetails.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDetailsDao userDetailsDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDetailsDao.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("NO USER FOUND");
        }
        return new CustomUserServices(user);
    }
}
