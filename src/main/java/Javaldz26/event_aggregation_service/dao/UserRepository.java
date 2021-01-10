package Javaldz26.event_aggregation_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository {


    @Query("SELECT u.id FROM User u WHERE u.email = ?1")
    boolean existsByEmail(String email);
}
