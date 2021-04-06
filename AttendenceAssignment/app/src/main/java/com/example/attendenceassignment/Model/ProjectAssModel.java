package com.example.attendenceassignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectAssModel {
    @SerializedName("data")
    @Expose
    private List<Datum> data ;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("msg")
    @Expose
    private String msg;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public class Datum {

        @SerializedName("inTime")
        @Expose
        private String inTime;
        @SerializedName("outTime")
        @Expose
        private String outTime;
        @SerializedName("minutes")
        @Expose
        private String minutes;
        @SerializedName("wrkedHrs")
        @Expose
        private String wrkedHrs;
        @SerializedName("attStatus")
        @Expose
        private String attStatus;
        @SerializedName("inTimeVal")
        @Expose
        private String inTimeVal;
        @SerializedName("outTimeVal")
        @Expose
        private String outTimeVal;
        @SerializedName("isLateMark")
        @Expose
        private String isLateMark;
        @SerializedName("reson")
        @Expose
        private String reson;
        @SerializedName("holiday")
        @Expose
        private String holiday;
        @SerializedName("dayDate")
        @Expose
        private String dayDate;

        public String getInTime() {
            return inTime;
        }

        public void setInTime(String inTime) {
            this.inTime = inTime;
        }

        public String getOutTime() {
            return outTime;
        }

        public void setOutTime(String outTime) {
            this.outTime = outTime;
        }

        public String getMinutes() {
            return minutes;
        }

        public void setMinutes(String minutes) {
            this.minutes = minutes;
        }

        public String getWrkedHrs() {
            return wrkedHrs;
        }

        public void setWrkedHrs(String wrkedHrs) {
            this.wrkedHrs = wrkedHrs;
        }

        public String getAttStatus() {
            return attStatus;
        }

        public void setAttStatus(String attStatus) {
            this.attStatus = attStatus;
        }

        public String getInTimeVal() {
            return inTimeVal;
        }

        public void setInTimeVal(String inTimeVal) {
            this.inTimeVal = inTimeVal;
        }

        public String getOutTimeVal() {
            return outTimeVal;
        }

        public void setOutTimeVal(String outTimeVal) {
            this.outTimeVal = outTimeVal;
        }

        public String getIsLateMark() {
            return isLateMark;
        }

        public void setIsLateMark(String isLateMark) {
            this.isLateMark = isLateMark;
        }

        public String getReson() {
            return reson;
        }

        public void setReson(String reson) {
            this.reson = reson;
        }

        public String getHoliday() {
            return holiday;
        }

        public void setHoliday(String holiday) {
            this.holiday = holiday;
        }

        public String getDayDate() {
            return dayDate;
        }

        public void setDayDate(String dayDate) {
            this.dayDate = dayDate;
        }

    }

}
