package Javaldz26.event_aggregation_service.dao;

import Javaldz26.event_aggregation_service.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    getAllEventsSortedByNearestg
}
