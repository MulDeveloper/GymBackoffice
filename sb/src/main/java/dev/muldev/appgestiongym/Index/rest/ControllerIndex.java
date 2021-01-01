/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Index.rest;

import dev.muldev.appgestiongym.clients.domain.ClientMembership;
import dev.muldev.appgestiongym.clients.domain.ServiceClient;
import dev.muldev.appgestiongym.memberships.domain.ServiceMembership;
import dev.muldev.appgestiongym.staff.domain.ServiceStaff;
import dev.muldev.appgestiongym.prices.domain.Prices;
import dev.muldev.appgestiongym.prices.domain.ServicePrices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ControllerIndex {

    private final ServiceMembership serviceMembership;
    private final ServicePrices serviceTarifas;
    private final ServiceClient serviceClient;
    private final ServiceStaff serviceStaff;

    public ControllerIndex(ServiceMembership serviceMembership, ServicePrices serviceTarifas, ServiceClient serviceClient, ServiceStaff serviceStaff) {
        this.serviceMembership = serviceMembership;
        this.serviceTarifas = serviceTarifas;
        this.serviceClient = serviceClient;
        this.serviceStaff = serviceStaff;
    }


    @GetMapping("/index")
    public ResponseEntity<Map<String, Object>> dataIndex(){
        return new ResponseEntity<>(returnMap(),HttpStatus.OK);
    }
    
    @GetMapping("/index/clientsLast")
    public ResponseEntity<Map<Integer, ClientMembership>> dataClient(){
        Map clienteData = new HashMap<String, ClientMembership>();
        //last 10 clients
        List<ClientMembership> clienteList = serviceClient.lastTen();

        for (int i=0;i<clienteList.size();i++){
            clienteData.put(i, clienteList.get(i));
        }
        return new ResponseEntity<>(clienteData,HttpStatus.OK);


    }
    
    public Map<String, Object> returnMap(){
        //active clients
        Map indexData = new HashMap<String, Object>();

        indexData.put("activos", ""+serviceMembership.totalClientesActivos());
        //billing data
        indexData.put("facturacion", ""+serviceMembership.totalFacturacion());

        Double expenses;
        expenses = serviceStaff.calculateSalaries();
        indexData.put("gastos", ""+expenses);
        
        //stats
        Map data = new HashMap<String, Integer>();
        
        for (Prices tarifas: serviceTarifas.getAll()){
            //we get the client with that membership plan
            
            int numClientes = serviceMembership.clientePorTarifa(tarifas.getIdtarifa());
            data.put(tarifas.getDescripcion(), numClientes);
        }
        
        indexData.put("graphTarifas", data);
        
        //stats by month
        
        indexData.put("graphMes", serviceMembership.retornaDatosMensuales());
        

        return indexData;
    }
   

    
    
}
