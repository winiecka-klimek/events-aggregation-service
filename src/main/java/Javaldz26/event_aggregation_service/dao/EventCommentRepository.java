package Javaldz26.event_aggregation_service.dao;

import Javaldz26.event_aggregation_service.entities.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCommentRepository extends JpaRepository<EventComment, Long> {
}
