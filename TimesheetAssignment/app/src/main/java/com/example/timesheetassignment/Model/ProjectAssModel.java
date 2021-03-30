package com.example.timesheetassignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectAssModel {
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

        @SerializedName("extraWork")
        @Expose
        private List<ExtraWork> extraWork = null;
        @SerializedName("workType")
        @Expose
        private List<WorkType> workType = null;
        @SerializedName("usersList")
        @Expose
        private List<UsersList> usersList = null;
        @SerializedName("project")
        @Expose
        private List<Project> project = null;
        @SerializedName("tsHrs")
        @Expose
        private List<String> tsHrs = null;
        @SerializedName("tsMinuts")
        @Expose
        private List<String> tsMinuts = null;

        public List<ExtraWork> getExtraWork() {
            return extraWork;
        }

        public void setExtraWork(List<ExtraWork> extraWork) {
            this.extraWork = extraWork;
        }

        public List<WorkType> getWorkType() {
            return workType;
        }

        public void setWorkType(List<WorkType> workType) {
            this.workType = workType;
        }

        public List<UsersList> getUsersList() {
            return usersList;
        }

        public void setUsersList(List<UsersList> usersList) {
            this.usersList = usersList;
        }

        public List<Project> getProject() {
            return project;
        }

        public void setProject(List<Project> project) {
            this.project = project;
        }

        public List<String> getTsHrs() {
            return tsHrs;
        }

        public void setTsHrs(List<String> tsHrs) {
            this.tsHrs = tsHrs;
        }

        public List<String> getTsMinuts() {
            return tsMinuts;
        }

        public void setTsMinuts(List<String> tsMinuts) {
            this.tsMinuts = tsMinuts;
        }

    }
    public class ExtraWork {

        @SerializedName("extaworkID")
        @Expose
        private String extaworkID;
        @SerializedName("extraworkName")
        @Expose
        private String extraworkName;

        public String getExtaworkID() {
            return extaworkID;
        }

        public void setExtaworkID(String extaworkID) {
            this.extaworkID = extaworkID;
        }

        public String getExtraworkName() {
            return extraworkName;
        }

        public void setExtraworkName(String extraworkName) {
            this.extraworkName = extraworkName;
        }

    }

    public class Project {

        @SerializedName("projectID")
        @Expose
        private String projectID;
        @SerializedName("projectName")
        @Expose
        private String projectName;

        public String getProjectID() {
            return projectID;
        }

        public void setProjectID(String projectID) {
            this.projectID = projectID;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

    }

    public class UsersList extends Project {

        @SerializedName("userID")
        @Expose
        private String userID;
        @SerializedName("displayName")
        @Expose
        private String displayName;

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

    }

    public class WorkType {

        @SerializedName("workTypeID")
        @Expose
        private String workTypeID;
        @SerializedName("workTypeName")
        @Expose
        private String workTypeName;

        public String getWorkTypeID() {
            return workTypeID;
        }

        public void setWorkTypeID(String workTypeID) {
            this.workTypeID = workTypeID;
        }

        public String getWorkTypeName() {
            return workTypeName;
        }

        public void setWorkTypeName(String workTypeName) {
            this.workTypeName = workTypeName;
        }

    }

}



