package com.example.leaveassignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectAssModel
{
        @SerializedName("data")
        @Expose
        private Data data;
        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("msg")
        @Expose
        private String msg;

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
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

    public class Data {

        @SerializedName("leaveDetails")
        @Expose
        private String leaveDetails;
        @SerializedName("leaveType")
        @Expose
        private List<LeaveType> leaveType = null;
        @SerializedName("emailIDS")
        @Expose
        private List<EmailID> emailIDS = null;
        @SerializedName("leaveStatusArr")
        @Expose
        private List<String> leaveStatusArr = null;
        @SerializedName("leaveApprovedBy")
        @Expose
        private String leaveApprovedBy;

        public String getLeaveDetails() {
            return leaveDetails;
        }

        public void setLeaveDetails(String leaveDetails) {
            this.leaveDetails = leaveDetails;
        }

        public List<LeaveType> getLeaveType() {
            return leaveType;
        }

        public void setLeaveType(List<LeaveType> leaveType) {
            this.leaveType = leaveType;
        }

        public List<EmailID> getEmailIDS() {
            return emailIDS;
        }

        public void setEmailIDS(List<EmailID> emailIDS) {
            this.emailIDS = emailIDS;
        }

        public List<String> getLeaveStatusArr() {
            return leaveStatusArr;
        }

        public void setLeaveStatusArr(List<String> leaveStatusArr) {
            this.leaveStatusArr = leaveStatusArr;
        }

        public String getLeaveApprovedBy() {
            return leaveApprovedBy;
        }

        public void setLeaveApprovedBy(String leaveApprovedBy) {
            this.leaveApprovedBy = leaveApprovedBy;
        }

    }

    public class EmailID {

        @SerializedName("displayName")
        @Expose
        private String displayName;
        @SerializedName("userEmail")
        @Expose
        private String userEmail;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

    }

    public class LeaveType {

        @SerializedName("leaveTypeID")
        @Expose
        private String leaveTypeID;
        @SerializedName("leaveTypeName")
        @Expose
        private String leaveTypeName;

        public String getLeaveTypeID() {
            return leaveTypeID;
        }

        public void setLeaveTypeID(String leaveTypeID) {
            this.leaveTypeID = leaveTypeID;
        }

        public String getLeaveTypeName() {
            return leaveTypeName;
        }

        public void setLeaveTypeName(String leaveTypeName) {
            this.leaveTypeName = leaveTypeName;
        }

    }

}
