package dev.muldev.appgestiongym.memberships.infrastructure;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name = "memberships")
@NamedQueries({
        @NamedQuery(name = "MatriculasGym.findAll", query = "SELECT m FROM MembershipEntity m"),
        @NamedQuery(name = "MatriculasGym.findByIdmatricula", query = "SELECT m FROM MembershipEntity m WHERE m.idmatricula = :idmatricula"),
        @NamedQuery(name = "MatriculasGym.findByIdcliente", query = "SELECT m FROM MembershipEntity m WHERE m.idcliente = :idcliente"),
        @NamedQuery(name = "MatriculasGym.findByIdtarifa", query = "SELECT m FROM MembershipEntity m WHERE m.idtarifa = :idtarifa"),
        @NamedQuery(name = "MatriculasGym.findByFechaAlta", query = "SELECT m FROM MembershipEntity m WHERE m.fechaAlta = :fechaAlta"),
        @NamedQuery(name = "MatriculasGym.orderFecha", query = "SELECT m FROM MembershipEntity m ORDER BY m.fechaAlta"),
        @NamedQuery(name = "MatriculasGym.selectPorMes", query = "SELECT COUNT(*) FROM MembershipEntity m WHERE extract(month from m.fechaAlta) = :mes"),
        @NamedQuery(name = "MatriculasGym.countActives", query = "SELECT COUNT(*) FROM MembershipEntity m WHERE m.fechaAbonadoHasta >= :fechaActual"),
        @NamedQuery(name = "MatriculasGym.getAllActives", query = "SELECT m FROM MembershipEntity m WHERE m.fechaAbonadoHasta >= :fechaActual")
        })
public class MembershipEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmatricula", nullable = false)
    private Integer idmatricula;

    @Column(name = "idcliente")
    private Integer idcliente;

    @Column(name = "idtarifa")
    private Integer idtarifa;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fecha_abonado_hasta")
    private Date fechaAbonadoHasta;

}
