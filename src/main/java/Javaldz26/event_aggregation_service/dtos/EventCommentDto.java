package Javaldz26.event_aggregation_service.dtos;

import java.time.LocalDateTime;

public class EventCommentDto {

    private String commentText;

    private String commentatorNickname;

    private LocalDateTime commentAdded;

    public EventCommentDto(String commentText, String commentatorNickname, LocalDateTime commentAdded) {
        this.commentText = commentText;
        this.commentatorNickname = commentatorNickname;
        this.commentAdded = commentAdded;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getCommentatorNickname() {
        return commentatorNickname;
    }

    public LocalDateTime getCommentAdded() {
        return commentAdded;
    }
}
