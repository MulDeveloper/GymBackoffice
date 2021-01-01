package dev.muldev.appgestiongym.incidents.domain;

import dev.muldev.appgestiongym.incidents.domain.Incident;

import java.util.List;

public interface ServiceIncidents {
    boolean save(Incident incident);
    List<Incident> listAll();
    List<Incident> filterByMonthAndYear(int month, int year);
    boolean setResolved(int id);
    Double getCosts();
}
