/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.AccesoPersonal;

import dev.muldev.appgestiongym.AccesoPersonal.AccesoPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author bunn3
 */
public interface DAOAcceso extends JpaRepository<AccesoPersonal, Integer>{
    
}
