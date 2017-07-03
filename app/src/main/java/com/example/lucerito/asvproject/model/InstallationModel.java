package com.example.lucerito.asvproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class InstallationModel implements Parcelable {

    private String appId;
    private String appVersion;
    private int badge;
    private String created;
    private String deviceToken;
    private String deviceType;
    private String modified;
    private String status;
    public ArrayList<String> subscriptions;
    private String timeZone;
    private String userId;
    private String id;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<String> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InstallationModel() {
        subscriptions = new ArrayList<String>();
    }

    public InstallationModel(String appId, String appVersion, int badge, String created,
                             String deviceToken, String deviceType, String modified, String status,
                             ArrayList<String> subscriptions, String timeZone, String userId, String id) {

        this.appId = appId;
        this.appVersion = appVersion;
        this.badge = badge;
        this.created = created;
        this.deviceToken = deviceToken;
        this.deviceType = deviceType;
        this.modified = modified;
        this.status = status;
        this.subscriptions = subscriptions;
        this.timeZone = timeZone;
        this.userId = userId;
        this.id = id;
    }

    @Override
    public String toString() {
        return "InstallationModel{" +
                "appId='" + appId + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", badge='" + badge + '\'' +
                ", created='" + created + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", modified='" + modified + '\'' +
                ", status='" + status + '\'' +
                ", subscriptions=" + subscriptions +
                ", timeZone='" + timeZone + '\'' +
                ", userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appId);
        dest.writeString(this.appVersion);
        dest.writeInt(this.badge);
        dest.writeString(this.created);
        dest.writeString(this.deviceToken);
        dest.writeString(this.deviceType);
        dest.writeString(this.modified);
        dest.writeString(this.status);
        dest.writeList(this.subscriptions);
        dest.writeString(this.timeZone);
        dest.writeString(this.userId);
        dest.writeString(this.id);
    }

    protected InstallationModel(Parcel in) {
        this.appId = in.readString();
        this.appVersion = in.readString();
        this.badge = in.readInt();
        this.created = in.readString();
        this.deviceToken = in.readString();
        this.deviceType = in.readString();
        this.modified = in.readString();
        this.status = in.readString();
        if (subscriptions == null) {
            subscriptions = new ArrayList<String>();
        }
        in.readList(subscriptions, String.class.getClassLoader());
        this.timeZone = in.readString();
        this.userId = in.readString();
        this.id = in.readString();
    }

    public static final Creator<InstallationModel> CREATOR = new Creator<InstallationModel>() {
        @Override
        public InstallationModel createFromParcel(Parcel source) {
            return new InstallationModel(source);
        }

        @Override
        public InstallationModel[] newArray(int size) {
            return new InstallationModel[size];
        }
    };
}
