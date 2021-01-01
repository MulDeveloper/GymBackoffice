package dev.muldev.appgestiongym.classes.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GymClassRepository extends JpaRepository<GymClassEntity, Integer> {
}
