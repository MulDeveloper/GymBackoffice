/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Clients.Infrastructure;

import dev.muldev.appgestiongym.Clients.Domain.Client;
import dev.muldev.appgestiongym.Clients.Domain.ClientMembership;
import dev.muldev.appgestiongym.Clients.Domain.ServiceClient;
import dev.muldev.appgestiongym.Clients.Infrastructure.Entities.ClientEntity;
import dev.muldev.appgestiongym.Memberships.Infrastructure.MembershipEntity;
import dev.muldev.appgestiongym.Prices.Domain.ServicePrices;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ServiceClientPostgre implements ServiceClient {
    
    private final ClientRepository repo;
    private final EntityManager em;
    private final ServicePrices serviceTarifas;

    public ServiceClientPostgre(ClientRepository repo, EntityManager em, ServicePrices serviceTarifas) {
        this.repo = repo;
        this.em = em;
        this.serviceTarifas = serviceTarifas;
    }

    @Override
    public List<ClientMembership> lastTen() {
        try{
            //we get the last 10 memberships
            Query q = em.createNamedQuery("MatriculasGym.orderFecha").setMaxResults(10);
            //we get the 10 clients related to that memberships
            List<MembershipEntity> memberships = q.getResultList();

            List <ClientMembership> clientMemberships = new ArrayList();
            for (MembershipEntity m: memberships){
                
                ClientMembership clientAux = new ClientMembership();
                ClientEntity cliente = repo.getOne(m.getIdcliente());
                
                clientAux.setId(m.getIdcliente());
                clientAux.setNombre(cliente.getNombreCliente() + " " + cliente.getApellidoCliente());
                clientAux.setTelefono(cliente.getTelCliente());
                clientAux.setFecha(m.getFechaAlta());
                clientAux.setStatus(m.getEstado());
                
                //we get the description of the membership
                clientAux.setTarifa(serviceTarifas.getById(m.getIdtarifa()).getDescripcion());
                clientMemberships.add(clientAux);
  
            }
            
            
            return clientMemberships;
            
        }
        catch(NoResultException e){
            return null;
        }
    }

    @Override
    public List<Client> searchByInput(String input) {
        //search with like
        Query q = em.createNamedQuery("ClientesGym.findByInput").setParameter("input", "%"+input+"%");
        q.setMaxResults(5);
        List <Client> lista;
        lista = q.getResultList();
        if (lista.size() == 0){
            //no results
            return null;
        }
        return lista;
    }

    @Override
    public List<Client> listAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<Client> list =
                repo.findAll()
                        .stream()
                        .map(source -> modelMapper.map(source, Client.class))
                        .collect(Collectors.toList());
        return list;
    }

    @Override
    public Client add(Client c) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            ClientEntity entity = modelMapper.map(c, ClientEntity.class);
            entity = repo.save(entity);
            c.setIdcliente(entity.getIdcliente());
            return c;
        }
        catch(Exception e){
            return null;
        }
    }

}
