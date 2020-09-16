/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Tarifas;

import java.util.List;


public interface ServiceTarifa {
    public Double getPrice(int id);
    public List<TarifasGym> getTarifas();
    public TarifasGym getById(int id);
}
