package dev.muldev.appgestiongym.login.domain;

public interface ServiceUserLogin {
    void add(UserLogin u);
    UserLogin loadByUsername(String username);
    boolean delByUsername(String username);
}
