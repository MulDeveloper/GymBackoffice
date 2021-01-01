package dev.muldev.appgestiongym.clientlogin.infrastructure;

import dev.muldev.appgestiongym.clientlogin.domain.ClientLogin;
import dev.muldev.appgestiongym.clientlogin.domain.ServiceClientLogin;
import dev.muldev.appgestiongym.clientlogin.infrastructure.entities.ClientLoginEntity;
import dev.muldev.appgestiongym.login.infrastructure.UserLoginEntity;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Service
public class ServiceClientLoginPostgre implements ServiceClientLogin {

    private final ClientLoginRepository repo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EntityManager entityManager;

    public ServiceClientLoginPostgre(ClientLoginRepository repo, BCryptPasswordEncoder bCryptPasswordEncoder, EntityManager entityManager) {
        this.repo = repo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.entityManager = entityManager;
    }

    @Override
    public boolean add(ClientLogin clientLogin) {
        try{
            clientLogin.setPassword(bCryptPasswordEncoder.encode(clientLogin.getPassword()));
            repo.save(new ModelMapper().map(clientLogin, ClientLoginEntity.class));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(String username) {
        ClientLogin clientLogin = getByUsername(username);
        if(clientLogin != null) {
            repo.delete(new ModelMapper().map(clientLogin, ClientLoginEntity.class));
            return true;
        }
        return false;
    }

    @Override
    public ClientLogin getByUsername(String username) {
        try {
            Query q = entityManager.createNamedQuery("ClientLogin.findByUsername")
                    .setParameter("username", username);
            ClientLogin clientLogin = new ModelMapper().map((ClientLoginEntity) q.getSingleResult(), ClientLogin.class);
            return clientLogin;
        }
        catch(NoResultException e){
            return null;
        }
    }
}
