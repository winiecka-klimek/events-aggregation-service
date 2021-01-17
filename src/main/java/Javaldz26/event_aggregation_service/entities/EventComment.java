package Javaldz26.event_aggregation_service.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_comments")
public class EventComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    private String commentText;

    private String commentatorNickname;

    private LocalDateTime commentAdded = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User commentator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentatorNickname() {
        return commentatorNickname;
    }

    public void setCommentatorNickname(String comentatorNickname) {
        this.commentatorNickname = comentatorNickname;
    }

    public LocalDateTime getCommentAdded() {
        return commentAdded;
    }

    public void setCommentAdded(LocalDateTime commentAdded) {
        this.commentAdded = commentAdded;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getCommentator() {
        return commentator;
    }

    public void setCommentator(User comentator) {
        this.commentator = comentator;
    }
}
