package dev.muldev.appgestiongym.login.infrastructure;

import dev.muldev.appgestiongym.login.domain.ServiceUserLogin;
import dev.muldev.appgestiongym.login.domain.UserLogin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("userDetail")
public class UserDetailServiceImpl implements UserDetailsService {
    private final ServiceUserLogin service;

    public UserDetailServiceImpl(ServiceUserLogin service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserLogin user;
        try {
            user = service.loadByUsername(s);
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), Collections.emptyList());
        }
        catch(Exception e){
            throw new UsernameNotFoundException("User not foun");
        }

    }
}
