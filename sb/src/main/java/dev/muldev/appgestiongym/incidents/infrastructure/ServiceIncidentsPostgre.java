package dev.muldev.appgestiongym.incidents.infrastructure;

import dev.muldev.appgestiongym.incidents.domain.ServiceIncidents;
import dev.muldev.appgestiongym.incidents.domain.Incident;
import dev.muldev.appgestiongym.incidents.infrastructure.entities.IncidentsEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceIncidentsPostgre implements ServiceIncidents {

    private final IncidentsRepository repo;
    private final ModelMapper modelMapper;
    private final EntityManager em;

    @Override
    public boolean save(Incident incident) {
        try{
            repo.save(modelMapper.map(incident, IncidentsEntity.class));
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    @Override
    public List<Incident> listAll() {
        List<Incident> list =
                repo.findAll()
                        .stream()
                        .map(source -> modelMapper.map(source, Incident.class))
                        .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Incident> filterByMonthAndYear(int month, int year) {
        Query q = em.createNamedQuery("IncidentsEntity.filterByMonth")
                .setParameter("month", month)
                .setParameter("currentYear", year);
        List <Incident> lista =
                (List) q.getResultList()
                        .stream()
                        .map(source -> modelMapper.map(source, Incident.class))
                        .collect(Collectors.toList());

        if (lista.size() == 0){
            //no results
            return null;
        }

        return lista;
    }

    @Override
    public boolean setResolved(int id) {
        IncidentsEntity entity = repo.getOne(id);
        entity.setStatus(true);
        repo.save(entity);
        return true;
    }

    @Override
    public Double getCosts() {
        Double res = 0.0;
        int month = java.time.LocalDateTime.now().getMonthValue();
        int year = java.time.LocalDateTime.now().getYear();
        List<Incident> incidentsMonth = filterByMonthAndYear(month, year);

        for(Incident i: incidentsMonth){
            res += i.getCost();
        }
        return res;
    }
}
