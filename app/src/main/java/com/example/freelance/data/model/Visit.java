package com.example.freelance.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Visit implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("contact_picture")
    @Expose
    private String contactPicture;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("brick")
    @Expose
    private String brick;
    @SerializedName("territory")
    @Expose
    private String territory;
    @SerializedName("actual_at")
    @Expose
    private Object actualAt;
    @SerializedName("due_date")
    @Expose
    private String dueDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("in_location")
    @Expose
    private Boolean inLocation;
    @SerializedName("feedback")
    @Expose
    private String feedback;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("long")
    @Expose
    private String _long;

    protected Visit(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        contact = in.readString();
        contactPicture = in.readString();
        type = in.readString();
        brick = in.readString();
        territory = in.readString();
        dueDate = in.readString();
        createdAt = in.readString();
        byte tmpInLocation = in.readByte();
        inLocation = tmpInLocation == 0 ? null : tmpInLocation == 1;
        feedback = in.readString();
        lat = in.readString();
        _long = in.readString();
    }

    public static final Creator<Visit> CREATOR = new Creator<Visit>() {
        @Override
        public Visit createFromParcel(Parcel in) {
            return new Visit(in);
        }

        @Override
        public Visit[] newArray(int size) {
            return new Visit[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPicture() {
        return contactPicture;
    }

    public void setContactPicture(String contactPicture) {
        this.contactPicture = contactPicture;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrick() {
        return brick;
    }

    public void setBrick(String brick) {
        this.brick = brick;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public Object getActualAt() {
        return actualAt;
    }

    public void setActualAt(Object actualAt) {
        this.actualAt = actualAt;
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

    public Boolean getInLocation() {
        return inLocation;
    }

    public void setInLocation(Boolean inLocation) {
        this.inLocation = inLocation;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLong() {
        return _long;
    }

    public void setLong(String _long) {
        this._long = _long;
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
        parcel.writeString(contact);
        parcel.writeString(contactPicture);
        parcel.writeString(type);
        parcel.writeString(brick);
        parcel.writeString(territory);
        parcel.writeString(dueDate);
        parcel.writeString(createdAt);
        parcel.writeByte((byte) (inLocation == null ? 0 : inLocation ? 1 : 2));
        parcel.writeString(feedback);
        parcel.writeString(lat);
        parcel.writeString(_long);
    }
}