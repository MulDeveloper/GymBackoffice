/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Matriculas;

import java.util.Map;

/**
 *
 * @author bunn3
 */
public interface ServiceMatricula {
    public int totalClientesActivos();
    public Double totalFacturacion();
    public int clientePorTarifa(int id);
    public Map<String, Integer> retornaDatosMensuales();
}
