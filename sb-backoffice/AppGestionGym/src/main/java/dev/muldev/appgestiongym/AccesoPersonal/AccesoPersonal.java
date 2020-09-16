/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.AccesoPersonal;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bunn3
 */
@Entity
@Table(name = "acceso_personal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccesoPersonal.findAll", query = "SELECT a FROM AccesoPersonal a"),
    @NamedQuery(name = "AccesoPersonal.findByIdacceso", query = "SELECT a FROM AccesoPersonal a WHERE a.idacceso = :idacceso"),
    @NamedQuery(name = "AccesoPersonal.findByIdpersonal", query = "SELECT a FROM AccesoPersonal a WHERE a.idpersonal = :idpersonal"),
    @NamedQuery(name = "AccesoPersonal.findByUsername", query = "SELECT a FROM AccesoPersonal a WHERE a.username = :username"),
    @NamedQuery(name = "AccesoPersonal.findByPasswordPersonal", query = "SELECT a FROM AccesoPersonal a WHERE a.passwordPersonal = :passwordPersonal")})
public class AccesoPersonal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idacceso")
    private Integer idacceso;
    @Column(name = "idpersonal")
    private Integer idpersonal;
    @Column(name = "username")
    private String username;
    @Column(name = "password_personal")
    private String passwordPersonal;

    public AccesoPersonal() {
    }

    public AccesoPersonal(Integer idacceso) {
        this.idacceso = idacceso;
    }

    public Integer getIdacceso() {
        return idacceso;
    }

    public void setIdacceso(Integer idacceso) {
        this.idacceso = idacceso;
    }

    public Integer getIdpersonal() {
        return idpersonal;
    }

    public void setIdpersonal(Integer idpersonal) {
        this.idpersonal = idpersonal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordPersonal() {
        return passwordPersonal;
    }

    public void setPasswordPersonal(String passwordPersonal) {
        this.passwordPersonal = passwordPersonal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idacceso != null ? idacceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccesoPersonal)) {
            return false;
        }
        AccesoPersonal other = (AccesoPersonal) object;
        if ((this.idacceso == null && other.idacceso != null) || (this.idacceso != null && !this.idacceso.equals(other.idacceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dev.muldev.appgestiongym.AccesoPersonal.AccesoPersonal[ idacceso=" + idacceso + " ]";
    }
    
}
