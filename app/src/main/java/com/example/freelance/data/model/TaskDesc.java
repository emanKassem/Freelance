package com.example.freelance.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskDesc {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("from_user")
    @Expose
    private String fromUser;
    @SerializedName("from_user_picture")
    @Expose
    private String fromUserPicture;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("completed_at")
    @Expose
    private String completedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("feedback")
    @Expose
    private String feedback;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
