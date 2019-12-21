package com.example.freelance.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

    public class Task {
        @SerializedName("send_from_name")
        @Expose
        private String sendFromName;
        @SerializedName("send_from_user_icon")
        @Expose
        private String sendFromUserIcon;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("due_date")
        @Expose
        private String dueDate;
        @SerializedName("created_at")
        @Expose
        private String createdAt;

        public String getSendFromName() {
            return sendFromName;
        }

        public void setSendFromName(String sendFromName) {
            this.sendFromName = sendFromName;
        }

        public String getSendFromUserIcon() {
            return sendFromUserIcon;
        }

        public void setSendFromUserIcon(String sendFromUserIcon) {
            this.sendFromUserIcon = sendFromUserIcon;
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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
    }

