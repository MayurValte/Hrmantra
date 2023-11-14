package com.hrmantra;

import com.hrmantra.userDetails.dao.UserDetailsDao;
import com.hrmantra.userDetails.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HrmantraApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmantraApplication.class, args);
	}

}
