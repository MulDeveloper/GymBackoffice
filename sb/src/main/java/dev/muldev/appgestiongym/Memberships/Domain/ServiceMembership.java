/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Memberships.Domain;

import java.util.Map;

/**
 *
 * @author bunn3
 */
public interface ServiceMembership {
    int totalClientesActivos();
    Double totalFacturacion();
    int clientePorTarifa(int id);
    Map<String, Integer> retornaDatosMensuales();
    int add(Membership m);
}
