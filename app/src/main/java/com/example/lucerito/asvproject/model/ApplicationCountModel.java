package com.example.lucerito.asvproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by cmorenol on 10/05/2016.
 */
public class ApplicationCountModel implements Parcelable {

    private int count;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ApplicationCountModel() {
    }

    public ApplicationCountModel(int count) {
      this.count = count;
    }

    @Override
    public String toString() {
        return "InstallationCountModel{" +
                "count=" + count +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
    }

    protected ApplicationCountModel(Parcel in) {
        this.count = in.readInt();
    }

    public static final Creator<ApplicationCountModel> CREATOR = new Creator<ApplicationCountModel>() {
        @Override
        public ApplicationCountModel createFromParcel(Parcel source) {
            return new ApplicationCountModel(source);
        }

        @Override
        public ApplicationCountModel[] newArray(int size) {
            return new ApplicationCountModel[size];
        }
    };
}
