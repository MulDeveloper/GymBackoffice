package dev.muldev.appgestiongym.classes.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ServiceGymClass {
    boolean add(GymClass gymClass);
    List<GymClass> listByWeek(Date from, Date to);
    boolean deleteById(int id);
    boolean copyWeek(List<GymClass> list);
    List<GymClass> findAll();
    Map<String, Integer> statsByClass();
}
