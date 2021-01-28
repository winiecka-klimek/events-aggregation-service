package Javaldz26.event_aggregation_service.dao;

import Javaldz26.event_aggregation_service.entities.UsersRegisteredForEvents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRegisteredForEventsRepository extends JpaRepository<UsersRegisteredForEvents, Long> {

    boolean existsByRegisteredUserEmail(String email);

    boolean existsByIdAndRegisteredUserEmail(Long id, String email);
}
