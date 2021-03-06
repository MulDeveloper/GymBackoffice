package dev.muldev.appgestiongym.login.rest;

import dev.muldev.appgestiongym.login.domain.ServiceUserLogin;
import dev.muldev.appgestiongym.login.domain.UserLogin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adduser")
public class UserControllerExt {

    private final ServiceUserLogin serviceUserLogin;
    private final BCryptPasswordEncoder encoder;

    public UserControllerExt(ServiceUserLogin serviceUserLogin, BCryptPasswordEncoder encoder) {
        this.serviceUserLogin = serviceUserLogin;
        this.encoder = encoder;
    }

    @PostMapping
    public void signUp(@RequestBody UserLogin user) {
        user.setPassword(encoder.encode(user.getPassword()));
        serviceUserLogin.add(user);
    }
}
