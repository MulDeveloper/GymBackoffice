/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.AccesoPersonal;

import dev.muldev.appgestiongym.AccesoPersonal.AccesoPersonal;
import dev.muldev.appgestiongym.AccesoPersonal.ServiceAccesoPersonal;
import dev.muldev.appgestiongym.AccesoPersonal.DAOAcceso;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccesoPostgre implements ServiceAccesoPersonal{
    
    @Autowired
    private DAOAcceso dao;
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public AccesoPersonal altaAcceso(AccesoPersonal acceso) {
        return dao.save(acceso);
    }

    @Override
    public AccesoPersonal buscaPorNomUsu(String nomusu) {
        try{
            Query q = em.createNamedQuery("AccesoPersonal.findByUsername")
                    .setParameter("username", nomusu);
            AccesoPersonal personal = (AccesoPersonal) q.getSingleResult();
            return personal;
        }
        catch(NoResultException e){
            return null;
        }
    }
    
}
