
package dev.muldev.appgestiongym.clients.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientMembership implements Serializable{
    private int id;
    private String nombre;
    private String tarifa;
    private Date fecha;
    private int telefono;
    private Date fechaAbonadoHasta;
    
}
