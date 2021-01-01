
package dev.muldev.appgestiongym.clients.rest;

import dev.muldev.appgestiongym.clients.domain.Client;
import java.util.List;
import java.util.Map;

import dev.muldev.appgestiongym.clients.domain.Credentials;
import dev.muldev.appgestiongym.clients.domain.CredentialsGenerator;
import dev.muldev.appgestiongym.clients.domain.ServiceClient;
import dev.muldev.appgestiongym.memberships.domain.Membership;
import dev.muldev.appgestiongym.memberships.domain.ServiceMembership;
import dev.muldev.appgestiongym.rabbitmq.RabbitConfig;
import dev.muldev.appgestiongym.rabbitmq.RabbitEmailConfig;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
@AllArgsConstructor
public class ClientRestController{

    private final ServiceMembership serviceMembership;
    private final ServiceClient serviceClient;
    private final RabbitTemplate template;

    @GetMapping
    public ResponseEntity<List<Client>> list() {
        return new ResponseEntity(serviceClient.listAll(), HttpStatus.ACCEPTED);
    }


    @PostMapping
    public ResponseEntity<Boolean> addClient(@RequestBody Client client,
                                            @RequestParam(name = "idprice") String id){
        int idPrice = Integer.parseInt(id);

        if(idPrice == 0){
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }

        Client c = serviceClient.add(client);
        //we need to create a membership with the price selected
        //default expiration date in 1 month

        Membership m = new Membership(null,
                c.getIdcliente(),
                idPrice,
                java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()),
                java.sql.Timestamp.valueOf(java.time.LocalDateTime.now().plusMonths(1)));


        //we persist the membership and add the id to our client
        c.setIdMatricula(serviceMembership.add(m));
        serviceClient.add(c);

        //we create the credentials
        Credentials cred = new CredentialsGenerator(c.getNifCliente(), c.getFechaNacimiento(), c.getEmailCliente()).GenerateCredentials();

        //we publish a domain event to persist the credentials
        template.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                cred);

        //we publish a domain event to send the credentials
        template.convertAndSend(RabbitEmailConfig.TOPIC_EXCHANGE_EMAIL,
                RabbitEmailConfig.ROUTING_KEY_EMAIL,
                cred);

        return new ResponseEntity(true, HttpStatus.CREATED);

    }


    @GetMapping("/search/{value}")
    public ResponseEntity<List<Client>> searchByInputValue(@PathVariable("value") String value){
        return new ResponseEntity(serviceClient.searchByInput(value.toLowerCase()), HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Integer>> generateMapStatsByAge(){
        return new ResponseEntity(serviceClient.generateMapStatsByAgeRange(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteUser(@RequestParam(name = "id") String id){
        int idUser = Integer.parseInt(id);
        Client client = serviceClient.getOne(idUser);
        //we publish a domain event to delete client login
        template.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,
                RabbitConfig.ROUTING_KEY_DELETED,
                client.getNifCliente());

        return new ResponseEntity(serviceClient.deleteById(idUser), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updateClient(@RequestBody Client client){
        serviceClient.add(client);
        return new ResponseEntity(true, HttpStatus.CREATED);
    }

    @GetMapping("/byId")
    public ResponseEntity<Client> getOne(@RequestParam("id") String id){
        int idUser = Integer.parseInt(id);
        Client c = serviceClient.getOne(idUser);
        if(c != null) {
            return new ResponseEntity(c, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }


}
