package com.example.myaccountassignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountModel {
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("isUserAuthTokenValid")
    @Expose
    private Boolean isUserAuthTokenValid;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIsUserAuthTokenValid() {
        return isUserAuthTokenValid;
    }

    public void setIsUserAuthTokenValid(Boolean isUserAuthTokenValid) {
        this.isUserAuthTokenValid = isUserAuthTokenValid;
    }

    public class Data {

        @SerializedName("nationality")
        @Expose
        private List<Nationality> nationality = null;
        @SerializedName("emirates")
        @Expose
        private List<Emirate> emirates = null;

        public List<Nationality> getNationality() {
            return nationality;
        }

        public void setNationality(List<Nationality> nationality) {
            this.nationality = nationality;
        }

        public List<Emirate> getEmirates() {
            return emirates;
        }

        public void setEmirates(List<Emirate> emirates) {
            this.emirates = emirates;
        }

    }
    public class Emirate {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public class Nationality {

        @SerializedName("countryID")
        @Expose
        private Integer countryID;
        @SerializedName("countryName")
        @Expose
        private String countryName;

        public Integer getCountryID() {
            return countryID;
        }

        public void setCountryID(Integer countryID) {
            this.countryID = countryID;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

    }
}
