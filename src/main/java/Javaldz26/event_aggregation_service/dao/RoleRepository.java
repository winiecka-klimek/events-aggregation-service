package Javaldz26.event_aggregation_service.dao;

import Javaldz26.event_aggregation_service.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository {

    Optional<Role> findRoleByRoleName(String roleName);
}
