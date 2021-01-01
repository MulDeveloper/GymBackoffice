package dev.muldev.appgestiongym.email.domain;

import org.springframework.scheduling.annotation.Async;

public interface ServiceEmail {
    @Async
    void sendEmailWithCredentials(String username, String to, String pass);
}
