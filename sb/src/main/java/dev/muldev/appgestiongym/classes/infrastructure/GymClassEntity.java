package dev.muldev.appgestiongym.classes.infrastructure;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "gym_classes")
@TypeDefs({
        @TypeDef(
                name = "int-array",
                typeClass = IntArrayType.class
        )
})
@NamedQueries({
        @NamedQuery(name = "GymClassEntity.findAll", query = "SELECT c FROM GymClassEntity c"),
        @NamedQuery(name = "GymClassEntity.findByIdclase", query = "SELECT c FROM GymClassEntity c WHERE c.idclass = :idclass"),
        @NamedQuery(name = "GymClassEntity.findByFechaClase", query = "SELECT c FROM GymClassEntity c WHERE c.dateClass = :dateClass"),
        @NamedQuery(name = "GymClassEntity.findByHoraClase", query = "SELECT c FROM GymClassEntity c WHERE c.timeClass = :timeClass"),
        @NamedQuery(name = "GymClassEntity.findByDuracion", query = "SELECT c FROM GymClassEntity c WHERE c.duration = :duration"),
        @NamedQuery(name = "GymClassEntity.findByIntensidad", query = "SELECT c FROM GymClassEntity c WHERE c.intensity = :intensity"),
        @NamedQuery(name = "GymClassEntity.findByDescripcion", query = "SELECT c FROM GymClassEntity c WHERE c.description = :description"),
        @NamedQuery(name = "GymClassEntity.findByMonitor", query = "SELECT c FROM GymClassEntity c WHERE c.staff = :staff"),
        @NamedQuery(name = "GymClassEntity.findByListaClientes", query = "SELECT c FROM GymClassEntity c WHERE c.clients = :clients"),
        @NamedQuery(name = "GymClassEntity.findByDateBetween", query = "SELECT c FROM GymClassEntity c where c.dateClass between :from and :to order by c.dateClass asc" ),
        @NamedQuery(name = "GymClassEntity.stats", query = "SELECT c FROM GymClassEntity c where c.description = :class order by c.clients desc" )
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymClassEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idclass;
    private Date dateClass;
    private String timeClass;
    private String duration;
    private Integer intensity;
    private String description;
    private Integer staff;
    @Type(type = "int-array")
    @Column(
            name = "clients",
            columnDefinition = "integer[]"
    )
    private Serializable clients;

}

