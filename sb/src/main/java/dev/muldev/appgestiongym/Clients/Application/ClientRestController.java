/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Clients.Application;

import dev.muldev.appgestiongym.Clients.Domain.Client;
import dev.muldev.appgestiongym.Clients.Domain.ServiceClient;

import java.util.List;

import dev.muldev.appgestiongym.Memberships.Domain.Membership;
import dev.muldev.appgestiongym.Memberships.Domain.ServiceMembership;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
public class ClientRestController {
    
    private final ServiceClient service;
    private final ServiceMembership serviceMembership;

    public ClientRestController(ServiceClient service, ServiceMembership serviceMembership) {
        this.service = service;
        this.serviceMembership = serviceMembership;
    }

    @GetMapping
    public ResponseEntity<List<Client>> list() {
        return new ResponseEntity(service.listAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<String> addClient(@RequestBody Client client,
                                            @RequestParam(name = "price") String price){

        int idPrice = Integer.parseInt(price);

        Client c = service.add(client);
        //we need to create a membership with the price selected
        //default status false
        Membership m = new Membership(null,
                c.getIdcliente(),
                idPrice,
                java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()),
                false);

        //we persist the membership and add the id to our client
        c.setIdMatricula(serviceMembership.add(m));
        service.add(c);

        return new ResponseEntity(c.getNombreCliente()+ " " + c.getApellidoCliente(), HttpStatus.CREATED);


    }

    @GetMapping("/search/{value}")
    public ResponseEntity<List<Client>> searchByInputValue(@PathVariable("value") String value){
        //we call the service and get 5 results
        List <Client> clients = service.searchByInput(value.toLowerCase());
        return new ResponseEntity(clients, HttpStatus.OK);
    }





}
