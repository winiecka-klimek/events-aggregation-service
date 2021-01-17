package Javaldz26.event_aggregation_service.dtos.request;

import Javaldz26.event_aggregation_service.entities.User;

import javax.validation.constraints.Size;

public class NewCommentForm {

    private String commentatorNickname;

    @Size(max = 500, message = "Maximum comment length is 500 characters")
    private String  commentText;

    private User commentator;

    public User getCommentator() {
        return commentator;
    }

    public void setCommentator(User commentator) {
        this.commentator = commentator;
    }

    public String getCommentatorNickname() {
        return commentatorNickname;
    }

    public void setCommentatorNickname(String commentatorNickname) {
        this.commentatorNickname = commentatorNickname;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "NewCommentForm{" +
                "commentatorNickname='" + commentatorNickname + '\'' +
                ", commentText='" + commentText + '\'' +
                ", commentator=" + commentator +
                '}';
    }
}
