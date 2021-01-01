package dev.muldev.appgestiongym.incidents.infrastructure;

import dev.muldev.appgestiongym.incidents.infrastructure.entities.IncidentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentsRepository extends JpaRepository<IncidentsEntity, Integer> {

}