package Javaldz26.event_aggregation_service.dao;

import Javaldz26.event_aggregation_service.entities.Event;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findEventByEventTitleContainingIgnoreCaseOrderByStartDate(String searchFraze);

    List<Event> findAllByToDateAfter(LocalDateTime currentTime, Sort startDate);
}
