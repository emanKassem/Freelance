package com.example.freelance.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageDesc {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("from_user")
    @Expose
    private String fromUser;
    @SerializedName("from_user_picture")
    @Expose
    private String fromUserPicture;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("replies")
    @Expose
    private List<Reply> replies = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getFromUserPicture() {
        return fromUserPicture;
    }

    public void setFromUserPicture(String fromUserPicture) {
        this.fromUserPicture = fromUserPicture;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }
}
