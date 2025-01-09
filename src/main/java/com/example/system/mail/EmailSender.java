package com.example.system.mail;

public interface EmailSender {
    void send(String to, String email, String subject);
}
