/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.prices.infrastructure;

import dev.muldev.appgestiongym.prices.infrastructure.Entities.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepository extends JpaRepository<PricesEntity, Integer>{
    
}
