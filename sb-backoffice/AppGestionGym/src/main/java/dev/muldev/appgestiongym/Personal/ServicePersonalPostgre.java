/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Personal;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicePersonalPostgre implements ServicePersonal{
    
    @Autowired
    private DAOPersonal dao;
    
    @Autowired
    private EntityManager em;

    @Override
    public PersonalGym altaPersonal(PersonalGym personal) {
        return dao.save(personal);
    }

    @Override
    public PersonalGym getOne(int id) {
        try{
            return dao.getOne(id);
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public Double calculoSueldos() {
        Double total = 0.0;
        try{
            Query q = em.createNamedQuery("PersonalGym.sumaSueldos");
            
            /*
            calculo de base de cotizacion 
            (Contrato indefinido, no esta contemplado el temporal,
            habria que alterar la tabla de personal indicando si el personal
            es temporal o indefinido.
            
            */
            
            Double totalTemporal = Double.parseDouble(q.getSingleResult().toString());
            
            //cotizaciones
            total = totalTemporal * 1.236; //seg social
            total = total + (totalTemporal * 0.055); // desempleo
            total = total + (totalTemporal * 0.02); // FOGASA
            total = total + (totalTemporal * 0.07); // Formacion profesional

            return total;
        }
        catch(NoResultException e){
            return 0.0;
        }
    }

    @Override
    public List<PersonalGym> listaPersonal() {
        return dao.findAll();
    }
    
   
    
}
