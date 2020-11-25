/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Memberships.Infrastructure;

import dev.muldev.appgestiongym.Memberships.Domain.Membership;
import dev.muldev.appgestiongym.Memberships.Domain.ServiceMembership;
import dev.muldev.appgestiongym.Prices.Domain.Prices;
import dev.muldev.appgestiongym.Prices.Domain.ServicePrices;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ServiceMembershipPostgre implements ServiceMembership {

    private final ServicePrices servicePrices;
    private final EntityManager em;
    private final MembershipGymRepository repo;

    public ServiceMembershipPostgre(ServicePrices servicePrices, EntityManager em, MembershipGymRepository repo) {
        this.servicePrices = servicePrices;
        this.em = em;
        this.repo = repo;
    }

    @Override
    public int totalClientesActivos() {
        try{
            Query q = em.createNamedQuery("MatriculasGym.cuentaActivos");
            return Integer.parseInt(q.getSingleResult().toString());
        }
        catch(NoResultException e){
            return -1;
        }
    }

    @Override
    public Double totalFacturacion() {
        Double total = 0.0;
        //obtenemos todas las matriculas activas y devolvemos un double con el total de facturacion
        List <MembershipEntity> matriculasActivas;
        try{
            Query q = em.createNamedQuery("MatriculasGym.findByEstado").setParameter("estado", true);
            matriculasActivas = q.getResultList();

            
            for(MembershipEntity m: matriculasActivas){
                total += servicePrices.getPrice(m.getIdtarifa());
            }
            
            return total;
            
        }
        catch(NoResultException e){
            return 0.0;
        }
    }

    @Override
    public int clientePorTarifa(int id) {
        try{
            Query q = em.createNamedQuery("MatriculasGym.findByIdtarifa").setParameter("idtarifa", id);
            return q.getResultList().size();
        }
        catch(NoResultException e){
            return -1;
        }
    }

    @Override
    public Map<String, Integer> retornaDatosMensuales() {
        //comprobamos cuantas matriculas hay con fecha de alta para cada mes
        
        Map <String, Integer> retorno = new HashMap();
        
        for (int i=1;i<=12;i++){
            Query q = em.createNamedQuery("MatriculasGym.selectPorMes").setParameter("mes", i);
            int ret = Integer.parseInt(""+q.getSingleResult());
            switch(i){
                case 1:
                    retorno.put("ENERO", ret);
                    break;
                case 2:
                    retorno.put("FEBRERO", ret);
                    break;
                case 3:
                    retorno.put("MARZO", ret);
                    break;
                case 4:
                    retorno.put("ABRIL", ret);
                    break;
                case 5:
                    retorno.put("MAYO", ret);
                    break;
                case 6:
                    retorno.put("JUNIO", ret);
                    break;
                case 7:
                    retorno.put("JULIO", ret);
                    break;
                case 8:
                    retorno.put("AGOSTO", ret);
                    break;   
                case 9:
                    retorno.put("SEPTIEMBRE", ret);
                    break;
                case 10:
                    retorno.put("OCTUBRE", ret);
                    break;
                case 11:
                    retorno.put("NOVIEMBRE", ret);
                    break;
                case 12:
                    retorno.put("DICIEMBRE", ret);
                    break;
            }
        }

        return retorno;

    }

    @Override
    public int add(Membership m) {
        ModelMapper modelMapper = new ModelMapper();
        return repo.save(modelMapper.map(m, MembershipEntity.class)).getIdmatricula();
    }


}
