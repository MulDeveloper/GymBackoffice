package dev.muldev.appgestiongym.Login.Infrastructure;

import dev.muldev.appgestiongym.Login.Domain.ServiceUserLogin;
import dev.muldev.appgestiongym.Login.Domain.UserLogin;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;


@Service
public class ServiceUserLoginPostgre implements ServiceUserLogin {

    private final UserLoginRepository repo;
    private final EntityManager em;

    public ServiceUserLoginPostgre(UserLoginRepository repo, EntityManager em) {
        this.repo = repo;
        this.em = em;
    }

    @Override
    public void add(UserLogin u) {
        ModelMapper mapper = new ModelMapper();
        UserLoginEntity entity = mapper.map(u, UserLoginEntity.class);
        repo.save(entity);
    }

    @Override
    public UserLogin loadByUsername(String username) {
        //obtenemos por el username
        try{
            Query q = em.createNamedQuery("UserLoginEntity.findByUsername")
                    .setParameter("username", username);
            UserLoginEntity loginEntity = (UserLoginEntity) q.getSingleResult();
            //mapping
            ModelMapper modelMapper = new ModelMapper();
            UserLogin login = modelMapper.map(loginEntity, UserLogin.class);
            return login;
        }
        catch(NoResultException e){
            return null;
        }
    }

    @Override
    public boolean delByUsername(String username) {
        try{
            Query q = em.createNamedQuery("UserLoginEntity.findByUsername")
                    .setParameter("username", username);
            UserLoginEntity loginEntity = (UserLoginEntity) q.getSingleResult();
            repo.deleteById(loginEntity.getId());
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
