package com.hrmantra.userDetails.controller;

import com.hrmantra.userDetails.model.User;
import com.hrmantra.userDetails.service.EmailService;
import com.hrmantra.userDetails.service.UserDetailsService;
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
    private UserDetailsService userDetailsService;

    @Autowired
    private EmailService emailService;

    @Value("${email.notification.subject}")
    String subject;

    @Value("${email.notification.body}")
    private String emailBody;

    @GetMapping("/userLogin")
    public ResponseEntity<User> userLogin(@RequestParam(name = "username") String username,  @RequestParam(name = "password") String password){
        User user = userDetailsService.getUserByEmailAndPassword(username, password);
        if (user!=null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUserDetails(){
        List<User> allUserDetails = userDetailsService.getAllUserDetails();
        if(allUserDetails==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allUserDetails,HttpStatus.OK);
    }

 @PostMapping("/signUp")
    public ResponseEntity<String> saveUserDetails(@RequestBody User user){
     User userByEmail = userDetailsService.getUserByEmail(user.getEmail());
        if (userByEmail!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with the same username already exists.");
        }
     User savedUser = userDetailsService.saveUserDetails(user);
     if (savedUser!=null){
         return ResponseEntity.ok("User registered successfully.");
     }else{
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
     }
 }

    @PutMapping("/forgotPassword")
    public ResponseEntity<String> updatePassword(@RequestBody User user) throws MessagingException {
        User checkUser = userDetailsService.getUserByEmail(user.getEmail());
        if(checkUser==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        checkUser.setPassword(user.getPassword());
        User userUpdate = userDetailsService.saveUserDetails(checkUser);
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

}
