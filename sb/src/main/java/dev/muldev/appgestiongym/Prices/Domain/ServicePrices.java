/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.prices.domain;

import java.util.List;


public interface ServicePrices {
    Double getPrice(int id);
    List<Prices> getAll();
    Prices getById(int id);
}
