package Javaldz26.event_aggregation_service.dao;

import Javaldz26.event_aggregation_service.entities.EventComment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventCommentRepository extends JpaRepository<EventComment, Long> {

    List<EventComment> findByEventId(Long eventId, Sort sort);
}
