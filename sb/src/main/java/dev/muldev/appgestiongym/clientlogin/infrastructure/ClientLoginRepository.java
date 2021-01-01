package dev.muldev.appgestiongym.clientlogin.infrastructure;

import dev.muldev.appgestiongym.clientlogin.infrastructure.entities.ClientLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientLoginRepository extends JpaRepository<ClientLoginEntity, UUID> {



}
