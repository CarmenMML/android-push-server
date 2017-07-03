package com.example.lucerito.asvproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by cmorenol on 10/05/2016.
 */
public class MessageModel implements Parcelable {

    private String title; // Required
    private String text; // Required
    private String sent_date; // Required
    private String id;// Optional
    public ArrayList<DeviceMessageModel> deviceMessages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSent_date() {
        return sent_date;
    }

    public void setSent_date(String sent_date) {
        this.sent_date = sent_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<DeviceMessageModel> getDeviceMessages() {
        return deviceMessages;
    }

    public void setDeviceMessages(ArrayList<DeviceMessageModel> deviceMessages) {
        this.deviceMessages = deviceMessages;
    }

    public MessageModel() {
        deviceMessages = new ArrayList<DeviceMessageModel>();
    }

    public MessageModel(String title, String text, String sent_date) {
        this.title = title;
        this.text = text;
        this.sent_date = sent_date;
       // this.id = id;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "title='" + title +  + '\'' +
                ", text='" + text +  + '\'' +
                ", sent_date='" + sent_date +  + '\'' +
                ", id='" + id +  + '\'' +
                ", deviceMessages=" + deviceMessages +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.text);
        dest.writeString(this.sent_date);
        dest.writeString(this.id);
        dest.writeList(this.deviceMessages);
    }

    protected MessageModel(Parcel in) {
        this.title = in.readString();
        this.text = in.readString();
        this.sent_date = in.readString();
        this.id = in.readString();
        if (deviceMessages == null) {
            deviceMessages = new ArrayList<DeviceMessageModel>();
        }
        in.readList(deviceMessages, String.class.getClassLoader());
    }

    public static final Creator<MessageModel> CREATOR = new Creator<MessageModel>() {
        @Override
        public MessageModel createFromParcel(Parcel source) {
            return new MessageModel(source);
        }

        @Override
        public MessageModel[] newArray(int size) {
            return new MessageModel[size];
        }
    };
}
