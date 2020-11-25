package dev.muldev.appgestiongym.Prices.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Prices {
    private Integer idtarifa;
    private BigDecimal total;
    private BigDecimal totalIva;
    private String descripcion;
    private Boolean permanencia;
}
