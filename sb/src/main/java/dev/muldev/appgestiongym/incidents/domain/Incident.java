package dev.muldev.appgestiongym.incidents.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incident {
    private Integer id;
    private Date dateIncident;
    private boolean status;
    private Double cost;
    private String description;
    
}
