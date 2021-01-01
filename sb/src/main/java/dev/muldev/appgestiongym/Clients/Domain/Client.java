package dev.muldev.appgestiongym.clients.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    private Integer idcliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String emailCliente;
    private String nifCliente;
    private Integer telCliente;
    private String direccionCliente;
    private Integer idMatricula;
    private Date fechaNacimiento;
}
