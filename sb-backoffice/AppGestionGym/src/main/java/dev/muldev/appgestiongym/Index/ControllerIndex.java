/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Index;

import dev.muldev.appgestiongym.Clientes.ClienteMatricula;
import dev.muldev.appgestiongym.Clientes.ServiceCliente;
import dev.muldev.appgestiongym.Jwt.CheckToken;
import dev.muldev.appgestiongym.Jwt.TokenUser;
import dev.muldev.appgestiongym.Matriculas.ServiceMatricula;
import dev.muldev.appgestiongym.Personal.ServicePersonal;
import dev.muldev.appgestiongym.Tarifas.ServiceTarifa;
import dev.muldev.appgestiongym.Tarifas.TarifasGym;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ControllerIndex {
    
    @Autowired
    private ServiceMatricula serviceMatriculas;
    
    @Autowired
    private ServiceTarifa serviceTarifas;
    
    @Autowired
    private ServiceCliente serviceCliente;
    
    @Autowired
    private ServicePersonal servicePersonal;
    
    
    @GetMapping("/index")
    public ResponseEntity<Map<String, Object>> dataIndex(@RequestParam(name="nomusu") String nomusu,
            @RequestParam(name="token") String token){
        
        CheckToken checkToken = new CheckToken();

        TokenUser tkn = new TokenUser(token,nomusu);
        
        //comprobamos que el token es valido y que tenemos acceso a la info
        if(checkToken.decodeJWT(tkn)){
            return new ResponseEntity<>(retornaDatosMap(),HttpStatus.OK);
        }
        
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        
    }
    
    @GetMapping("/index/clientesLast")
    public ResponseEntity<Map<Integer, ClienteMatricula>> dataCliente(@RequestParam(name="nomusu") String nomusu,
            @RequestParam(name="token") String token){
        
        CheckToken checkToken = new CheckToken();
        
        TokenUser tkn = new TokenUser(token,nomusu);

        //comprobamos que el token es valido
        if(checkToken.decodeJWT(tkn)){
            Map clienteData = new HashMap<String, ClienteMatricula>();
            
            //ultimos 10 clientes
            List<ClienteMatricula> clienteList = serviceCliente.lastTen();

            for (int i=0;i<clienteList.size();i++){
                clienteData.put(i, clienteList.get(i));
            }
            
            return new ResponseEntity<>(clienteData,HttpStatus.OK);
        }
        
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

            
        

    }
    
    public Map<String, Object> retornaDatosMap(){
        //clientes activos
        Map indexData = new HashMap<String, Object>();

        indexData.put("activos", ""+serviceMatriculas.totalClientesActivos());

        //facturacion

        indexData.put("facturacion", ""+serviceMatriculas.totalFacturacion());

        //visitas web

        //gastos personal
        Double gastos = 0.0;
        gastos = servicePersonal.calculoSueldos();
        indexData.put("gastos", ""+gastos);
        
        //estadisticas por tarifa
        
        Map tarifaData = new HashMap<String, Integer>();
        
        for (TarifasGym tarifas: serviceTarifas.getTarifas()){
            //obtenemos los clientes con esa tarifa
            
            int numClientes = serviceMatriculas.clientePorTarifa(tarifas.getIdtarifa());
            
            //ponemos los datos
            tarifaData.put(tarifas.getDescripcion(), numClientes);
        }
        
        indexData.put("graphTarifas", tarifaData);
        
        //estadisticas mes a mes
        
        indexData.put("graphMes", serviceMatriculas.retornaDatosMensuales());
        

        return indexData;
    }
   

    
    
}
