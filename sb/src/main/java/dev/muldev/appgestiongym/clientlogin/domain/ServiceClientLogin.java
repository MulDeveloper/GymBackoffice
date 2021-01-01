package dev.muldev.appgestiongym.clientlogin.domain;

public interface ServiceClientLogin {
    boolean add(ClientLogin clientLogin);
    boolean delete(String username);
    ClientLogin getByUsername(String username);
}
