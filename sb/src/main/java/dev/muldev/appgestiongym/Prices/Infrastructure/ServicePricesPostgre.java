/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.prices.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import dev.muldev.appgestiongym.prices.domain.Prices;
import dev.muldev.appgestiongym.prices.domain.ServicePrices;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ServicePricesPostgre implements ServicePrices {

    private final PricesRepository repo;

    public ServicePricesPostgre(PricesRepository repo) {
        this.repo = repo;
    }

    @Override
    public Double getPrice(int id) {
        return Double.parseDouble(repo.getOne(id).getTotalIva().toString());
    }

    @Override
    public List<Prices> getAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<Prices> list =
                repo.findAll()
                        .stream()
                        .map(source -> modelMapper.map(source, Prices.class))
                        .collect(Collectors.toList());
        return list;
    }

    @Override
    public Prices getById(int id) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(repo.getOne(id), Prices.class);
    }
    
}
