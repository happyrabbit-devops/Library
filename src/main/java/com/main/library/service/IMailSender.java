package com.main.library.service;

public interface IMailSender {

    void send(String emailTo, String subject, String message);
}
