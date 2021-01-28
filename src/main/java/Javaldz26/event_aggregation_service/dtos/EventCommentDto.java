package Javaldz26.event_aggregation_service.dtos;

import java.time.LocalDateTime;

public class EventCommentDto {

    private Long commentID;

    private String commentText;

    private String commentatorNickname;

    private LocalDateTime commentAdded;

    public EventCommentDto(Long commentID, String commentText, String commentatorNickname, LocalDateTime commentAdded) {
        this.commentID = commentID;
        this.commentText = commentText;
        this.commentatorNickname = commentatorNickname;
        this.commentAdded = commentAdded;
    }

    public String getCommentText() {
        return commentText;
    }

    public Long getCommentID() {
        return commentID;
    }

    public String getCommentatorNickname() {
        return commentatorNickname;
    }

    public LocalDateTime getCommentAdded() {
        return commentAdded;
    }
}
