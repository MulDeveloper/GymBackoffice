/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Personal;

import java.util.List;

/**
 *
 * @author bunn3
 */
public interface ServicePersonal {
    public PersonalGym altaPersonal(PersonalGym personal);
    public PersonalGym getOne(int id);
    public Double calculoSueldos();
    public List<PersonalGym> listaPersonal();
}
