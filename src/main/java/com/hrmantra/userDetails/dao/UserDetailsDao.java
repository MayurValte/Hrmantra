package com.hrmantra.userDetails.dao;

import com.hrmantra.userDetails.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsDao extends JpaRepository<User,Long> {
    User findByEmail(@Param("email") String email);

    User findByUsername(@Param("username") String username);

//    User saveUserDetails(@Param("user") User user);
}
