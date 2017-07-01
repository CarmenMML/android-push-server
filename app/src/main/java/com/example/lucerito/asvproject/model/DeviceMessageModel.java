package com.example.lucerito.asvproject.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cmorenol on 10/05/2016.
 */
public class DeviceMessageModel implements Parcelable {

    private String deviceTokenId;
    private String messageId;
    private boolean sent;
    private boolean read;
    private boolean deleted;
    private String id;

    public String getDeviceTokenId() {
        return deviceTokenId;
    }

    public void setDeviceTokenId(String deviceTokenId) {
        this.deviceTokenId = deviceTokenId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceMessageModel() {
    }

    @Override
    public String toString() {
        return "DeviceMessageModel{" +
                "deviceTokenId='" + deviceTokenId +  + '\'' +
                "messageId='" + messageId +  + '\'' +
                "sent=" + sent +
                "read=" + read +
                "deleted=" + deleted +
                "id='" + id +  + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.deviceTokenId);
        dest.writeString(this.messageId);
        dest.writeByte((byte) (sent ? 1 : 0));
        dest.writeByte((byte) (read ? 1 : 0));
        dest.writeByte((byte) (deleted ? 1 : 0));
        dest.writeString(this.id);
    }

    protected DeviceMessageModel(Parcel in) {
        this.deviceTokenId = in.readString();
        this.messageId = in.readString();
        sent = in.readByte() != 0;
        read = in.readByte() != 0;
        deleted = in.readByte() != 0;
        this.id = in.readString();
    }

    public static final Creator<DeviceMessageModel> CREATOR = new Creator<DeviceMessageModel>() {
        @Override
        public DeviceMessageModel createFromParcel(Parcel source) {
            return new DeviceMessageModel(source);
        }

        @Override
        public DeviceMessageModel[] newArray(int size) {
            return new DeviceMessageModel[size];
        }
    };
}
