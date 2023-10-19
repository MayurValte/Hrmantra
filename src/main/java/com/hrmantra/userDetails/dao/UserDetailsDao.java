package com.hrmantra.userDetails.dao;

import com.hrmantra.userDetails.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDetailsDao extends JpaRepository<User,Long> {
    User findByEmailAndPassword(@Param("username") String username, @Param("password") String password);

    User findByEmail(@Param("email") String email);

//    User saveUserDetails(@Param("user") User user);
}
