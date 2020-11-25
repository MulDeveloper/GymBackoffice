package dev.muldev.appgestiongym.Memberships.Infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MembershipGymRepository extends JpaRepository<MembershipEntity, Integer>, JpaSpecificationExecutor<MembershipEntity> {

}