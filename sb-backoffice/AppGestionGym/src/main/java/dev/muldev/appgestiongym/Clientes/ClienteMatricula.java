
package dev.muldev.appgestiongym.Clientes;

import java.io.Serializable;
import java.util.Date;


public class ClienteMatricula implements Serializable{
    private int id;
    private String nombre;
    private String tarifa;
    private Date fecha;
    private int telefono;
    private boolean status;

    public ClienteMatricula() {
    }

    public ClienteMatricula(int id, String nombre, String tarifa, Date fecha, int telefono, boolean status) {
        this.id = id;
        this.nombre = nombre;
        this.tarifa = tarifa;
        this.fecha = fecha;
        this.telefono = telefono;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
   
    
    
    
    
}
