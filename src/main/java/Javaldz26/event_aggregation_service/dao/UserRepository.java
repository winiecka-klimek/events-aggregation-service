package Javaldz26.event_aggregation_service.dao;

import Javaldz26.event_aggregation_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u.id FROM User u WHERE u.email = ?1")
    boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);
}
