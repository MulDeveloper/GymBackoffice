/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.staff.domain;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Staff {
    private Integer idpersonal;
    private String nombrePersonal;
    private String apellidoPersonal;
    private String emailPersonal;
    private String nifPersonal;
    private Integer telPersonal;
    private String direccionPersonal;
    private BigDecimal sueldoBruto;
    private Date fechaNacimiento;
    private Date fechaAlta;
    private String rol;
    
}
