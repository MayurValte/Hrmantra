package com.hrmantra.userDetails.controller;

import com.hrmantra.userDetails.model.User;
import com.hrmantra.userDetails.service.EmailService;
import com.hrmantra.userDetails.service.UserServices;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hrmantra")
public class UserDetailsController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private EmailService emailService;

    @Value("${email.notification.subject}")
    String subject;

    @Value("${email.notification.body}")
    private String emailBody;


    @GetMapping("/user/get/getAllUsers")
    public ResponseEntity<List<User>> getAllUserDetails(){
        List<User> allUserDetails = userServices.getAllUserDetails();
        if(allUserDetails==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allUserDetails,HttpStatus.OK);
    }

    @PostMapping("/user/post/signUp")
    public ResponseEntity<String> saveUserDetails(@RequestBody User user){
     User userByUsername = userServices.getUserByEmail(user.getEmail());
        if (userByUsername!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with the same username already exists.");
        }
     User savedUser = userServices.saveUserDetails(user);
     if (savedUser!=null){
         return ResponseEntity.ok("User registered successfully.");
     }else{
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
     }
 }

    @PutMapping("/user/get/forgotPassword")
    public ResponseEntity<String> updatePassword(@RequestBody User user) throws MessagingException {
        User checkUser = userServices.getUserByEmail(user.getEmail());
        if(checkUser==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        checkUser.setPassword(user.getPassword());
        User userUpdate = userServices.updateUserDetails(checkUser);
        if(userUpdate==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong while updating password");
        }
        String body = "Dear " +
                checkUser.getFirstName() + " " + checkUser.getLastName() +" \n"+
                emailBody;

        emailService.sendEmail(user.getEmail(), subject, body);
        System.out.println("Mail Sent");


        return ResponseEntity.ok("Password updated successful");
    }

    @GetMapping("/updateRole")
    public ResponseEntity<String> updateRole(@RequestParam() String empId, @RequestParam() String role){
        User user = userServices.getUserByEmpId(Long.valueOf(empId));
        if (user!=null){
            if(role.startsWith("ROLE_")){
                user.setRole(role);
            }else {
                user.setRole("ROLE_"+role);
            }
        }
        User savedUser = this.userServices.updateRole(user);
        if(savedUser==null){
            return new ResponseEntity<>("Couldn't update role", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok("Successfully updated role");
    }

}
