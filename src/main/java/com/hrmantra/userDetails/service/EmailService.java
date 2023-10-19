package com.hrmantra.userDetails.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendEmail(String to,String subject,String text) throws MessagingException;
}
