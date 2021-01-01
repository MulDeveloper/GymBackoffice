package dev.muldev.appgestiongym.classes.infrastructure;

import dev.muldev.appgestiongym.classes.domain.GymClass;
import dev.muldev.appgestiongym.classes.domain.ServiceGymClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ServiceGymClassPostgre implements ServiceGymClass {

    private final GymClassRepository repo;
    private final EntityManager entityManager;

    public ServiceGymClassPostgre(GymClassRepository repo, EntityManager entityManager) {
        this.repo = repo;
        this.entityManager = entityManager;
    }

    @Override
    public boolean add(GymClass gymClass) {
        repo.save(new ModelMapper().map(gymClass, GymClassEntity.class));
        return true;
    }

    @Override
    public List<GymClass> listByWeek(Date from, Date to) {
        try{
            Query q = entityManager.createNamedQuery("GymClassEntity.findByDateBetween").setParameter("from", from)
                    .setParameter("to", to);
            ModelMapper modelMapper = new ModelMapper();

            List<GymClass> list =
                    (List) q.getResultList()
                            .stream()
                            .map(source -> modelMapper.map(source, GymClass.class))
                            .collect(Collectors.toList());
            return list;


        }
        catch(NoResultException e){
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            repo.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    @Override
    public boolean copyWeek(List<GymClass> list) {
        for(GymClass c: list){
            String[] dateFormat = c.getDateClass().toString().split(" ");
            String newDate = LocalDate.parse(dateFormat[0]).plusDays(7).toString();
            try {
                GymClass gymClass = new GymClass();
                gymClass.setDateClass(new SimpleDateFormat("yyyy-MM-dd").parse(newDate));
                gymClass.setDescription(c.getDescription());
                Integer[] clients = {};
                gymClass.setClients(clients);
                gymClass.setTimeClass(c.getTimeClass());
                gymClass.setDuration(c.getDuration());
                gymClass.setIntensity(c.getIntensity());
                gymClass.setStaff(c.getStaff());
                add(gymClass);
            }
            catch (ParseException e){
                return false;
            }

        }
        return true;
    }

    @Override
    public List<GymClass> findAll() {
        List<GymClass> list =
                repo.findAll()
                        .stream()
                        .map(source -> new ModelMapper().map(source, GymClass.class))
                        .collect(Collectors.toList());
        return list;
    }

    @Override
    public Map<String, Integer> statsByClass() {
        try{
            Map<String, Integer> map = new HashMap<>();
            String [] gymClasses = {"ZUMBA", "HALTEROFILIA", "BODY-PUMP", "CROSSFIT", "CYCLING"};
            for (String s: gymClasses) {
                Query q = entityManager.createNamedQuery("GymClassEntity.stats").setParameter("class", s)
                        .setMaxResults(1);
                GymClassEntity res = (GymClassEntity) q.getSingleResult();
                Integer[] clients = (Integer[]) res.getClients();
                map.put(s, clients.length);
            }
            return map;

        }
        catch(NoResultException e){
            return null;
        }
    }
}
