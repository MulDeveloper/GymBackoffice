/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Tarifas;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTarifaPostgre implements ServiceTarifa{
    
    @Autowired
    private DAOTarifa dao;

    @Override
    public Double getPrice(int id) {
        return Double.parseDouble(dao.getOne(id).getTotalIva().toString());
    }

    @Override
    public List<TarifasGym> getTarifas() {
        return dao.findAll();
    }

    @Override
    public TarifasGym getById(int id) {
        return dao.getOne(id);
    }
    
}
