package dev.muldev.appgestiongym.incidents.infrastructure.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "incidents")
@Data
@NamedQueries({
        @NamedQuery(name = "IncidentsEntity.filterByMonth",
                query = "SELECT i FROM IncidentsEntity i " +
                        "WHERE extract(month from i.dateIncident) = :month and extract(year from i.dateIncident) = :currentYear order by i.status")
})
public class IncidentsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date_incident")
    private Date dateIncident;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "description")
    private String description;

}
