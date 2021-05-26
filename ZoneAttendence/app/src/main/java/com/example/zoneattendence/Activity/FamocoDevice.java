package com.example.zoneattendence.Activity;

import android.os.Parcel;
import android.os.Parcelable;

public class FamocoDevice implements Parcelable {
    private String famocoId;
    private String model;
    public static final Creator<FamocoDevice> CREATOR = new Creator<FamocoDevice>() {
        public FamocoDevice createFromParcel(Parcel in) {
            return new FamocoDevice(in);
        }

        public FamocoDevice[] newArray(int size) {
            return new FamocoDevice[size];
        }
    };

    protected FamocoDevice(Parcel in) {
        this.famocoId = in.readString();
        this.model = in.readString();
    }

    public FamocoDevice() {
    }

    public String getFamocoId() {
        return this.famocoId;
    }

    public void setFamocoId(String famocoId) {
        this.famocoId = famocoId;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.famocoId);
        parcel.writeString(this.model);
    }

    public void readFromParcel(Parcel in) {
        this.famocoId = in.readString();
        this.model = in.readString();
    }
}
