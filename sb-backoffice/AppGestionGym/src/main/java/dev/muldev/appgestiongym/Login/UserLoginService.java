package dev.muldev.appgestiongym.Login;


import dev.muldev.appgestiongym.AccesoPersonal.AccesoPersonal;
import dev.muldev.appgestiongym.AccesoPersonal.ServiceAccesoPostgre;
import dev.muldev.appgestiongym.Personal.PersonalGym;
import dev.muldev.appgestiongym.Personal.ServicePersonalPostgre;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userLoginService")
public class UserLoginService implements UserDetailsService{
    
    //service acceso personal
    
    @Autowired
    private ServiceAccesoPostgre service;
    
    @Autowired
    private ServicePersonalPostgre servicePersonal;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        
        AccesoPersonal accesoPersonal = new AccesoPersonal();
        PersonalGym personal = new PersonalGym();
        
        try{
            accesoPersonal = service.buscaPorNomUsu(string);
            personal = servicePersonal.getOne(accesoPersonal.getIdpersonal());
        }
        catch(NullPointerException e){
            throw new UsernameNotFoundException("Usuario no existe");
        }
         
         
        List <GrantedAuthority> roles = new ArrayList();
        
        roles.add(new SimpleGrantedAuthority(personal.getRol()));
        
        if(roles.isEmpty()){
            throw new UsernameNotFoundException("No tienes permisos");
        }
        
        return new User(accesoPersonal.getUsername(), accesoPersonal.getPasswordPersonal(),true, true, true, true, roles);

        
    }
    
    
    
    
    
}
