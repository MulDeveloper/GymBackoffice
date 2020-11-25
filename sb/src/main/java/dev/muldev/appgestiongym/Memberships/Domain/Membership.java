package dev.muldev.appgestiongym.Memberships.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Membership {
    private Integer idmatricula;
    private Integer idcliente;
    private Integer idtarifa;
    private Date fechaAlta;
    private Boolean estado;
}
