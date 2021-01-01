package dev.muldev.appgestiongym.classes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GymClass {
    private Integer idclass;
    private Date dateClass;
    private String timeClass;
    private String duration;
    private Integer intensity;
    private String description;
    private int staff;
    private Integer[] clients;

}
