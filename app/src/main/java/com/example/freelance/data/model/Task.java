package com.example.freelance.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

    public class Task implements Parcelable {
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

        protected Task(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            fromUser = in.readString();
            fromUserPicture = in.readString();
            title = in.readString();
            dueDate = in.readString();
            completedAt = in.readString();
            createdAt = in.readString();
        }

        public static final Creator<Task> CREATOR = new Creator<Task>() {
            @Override
            public Task createFromParcel(Parcel in) {
                return new Task(in);
            }

            @Override
            public Task[] newArray(int size) {
                return new Task[size];
            }
        };

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            if (id == null) {
                parcel.writeByte((byte) 0);
            } else {
                parcel.writeByte((byte) 1);
                parcel.writeInt(id);
            }
            parcel.writeString(fromUser);
            parcel.writeString(fromUserPicture);
            parcel.writeString(title);
            parcel.writeString(dueDate);
            parcel.writeString(completedAt);
            parcel.writeString(createdAt);
        }
    }

