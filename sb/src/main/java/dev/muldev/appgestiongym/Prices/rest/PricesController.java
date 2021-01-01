package dev.muldev.appgestiongym.prices.rest;

import dev.muldev.appgestiongym.prices.domain.Prices;
import dev.muldev.appgestiongym.prices.domain.ServicePrices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
public class PricesController {

    private final ServicePrices service;

    public PricesController(ServicePrices service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Prices>> getAllPrices(){
        return new ResponseEntity(service.getAll(), HttpStatus.ACCEPTED);
    }
}
