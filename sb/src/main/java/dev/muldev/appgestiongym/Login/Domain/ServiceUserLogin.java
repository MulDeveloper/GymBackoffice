package dev.muldev.appgestiongym.Login.Domain;

public interface ServiceUserLogin {
    void add(UserLogin u);
    UserLogin loadByUsername(String username);
    boolean delByUsername(String username);
}
