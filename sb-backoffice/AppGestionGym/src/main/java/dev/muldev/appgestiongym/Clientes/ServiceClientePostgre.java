/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Clientes;

import dev.muldev.appgestiongym.Matriculas.MatriculasGym;
import dev.muldev.appgestiongym.Tarifas.ServiceTarifa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClientePostgre implements ServiceCliente{
    
    @Autowired
    private DAOCliente dao;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private ServiceTarifa serviceTarifas;

    @Override
    public List<ClienteMatricula> lastTen() {
        try{
            //obtenemos las ultimas 10 matriculas
            Query q = em.createNamedQuery("MatriculasGym.orderFecha").setMaxResults(10);
            //obtenemos los 10 clientes asociados a esas matriculas
            List<MatriculasGym> matriculas = q.getResultList();
            
            
            List <ClienteMatricula> clienteMatriculas = new ArrayList();
            for (MatriculasGym m: matriculas){
                
                ClienteMatricula clienteAux = new ClienteMatricula();
                
                ClientesGym cliente = dao.getOne(m.getIdcliente());
                
                clienteAux.setId(m.getIdcliente());
                clienteAux.setNombre(cliente.getNombreCliente());
                clienteAux.setTelefono(cliente.getTelCliente());
                clienteAux.setFecha(m.getFechaAlta());
                clienteAux.setStatus(m.getEstado());
                
                //obtenemos la tarifa
                
                clienteAux.setTarifa(serviceTarifas.getById(m.getIdtarifa()).getDescripcion());
                
                clienteMatriculas.add(clienteAux);
  
            }
            
            
            return clienteMatriculas;
            
        }
        catch(NoResultException e){
            return null;
        }
    }
    
}
