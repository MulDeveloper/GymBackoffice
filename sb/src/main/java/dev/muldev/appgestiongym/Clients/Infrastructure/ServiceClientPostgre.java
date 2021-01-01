/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.clients.infrastructure;

import dev.muldev.appgestiongym.clients.domain.Client;
import dev.muldev.appgestiongym.clients.domain.ClientMembership;
import dev.muldev.appgestiongym.clients.domain.ServiceClient;
import dev.muldev.appgestiongym.clients.infrastructure.entities.ClientEntity;
import dev.muldev.appgestiongym.memberships.domain.ServiceMembership;
import dev.muldev.appgestiongym.memberships.infrastructure.MembershipEntity;
import dev.muldev.appgestiongym.prices.domain.ServicePrices;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final ServiceMembership serviceMembership;

    public ServiceClientPostgre(ClientRepository repo, EntityManager em, ServicePrices serviceTarifas, ServiceMembership serviceMembership) {
        this.repo = repo;
        this.em = em;
        this.serviceTarifas = serviceTarifas;
        this.serviceMembership = serviceMembership;
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

                clientAux.setFechaAbonadoHasta(m.getFechaAbonadoHasta());
                
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

    @Override
    public Client getOne(int id) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            ClientEntity entity = repo.getOne(id);
            Client client = modelMapper.map(entity, Client.class);
            return client;
        }
        catch(Exception e){
            return null;
        }
    }

    @Override
    public Boolean deleteById(int id) {
        try{
            if(serviceMembership.delByIdClient(id)) {
                repo.deleteById(id);
                return true;
            }
            return false;
        }
        catch(Exception e) {
            return false;
        }
    }

    @Override
    public Map<String, Integer> generateMapStatsByAgeRange() {
        //map with the specified age ranges
        int[] ageRanges = {16,30,45,60};
        Map<String, Integer> map = new HashMap<>();
        for(int i=0;i<ageRanges.length;i++) {
            if(i == 3){
                Long res;
                int clients = 0;
                //out of array, so max age (120)
                Query q = em.createNamedQuery("ClientesGym.countByAge")
                        .setParameter("minage", ageRanges[i])
                        .setParameter("maxage", 120);
                res = (Long) q.getSingleResult();
                clients = res.intValue();
                map.put("+60", clients);
            }
            else {
                Long res;
                int clients = 0;
                Query q = em.createNamedQuery("ClientesGym.countByAge")
                        .setParameter("minage", ageRanges[i])
                        .setParameter("maxage", ageRanges[i + 1]-1);
                res = (Long) q.getSingleResult();
                clients = res.intValue();
                map.put(""+ageRanges[i]+"-"+(ageRanges[i+1]), clients);
            }
        }
        return map;
    }

}
