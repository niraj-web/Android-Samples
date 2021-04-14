package com.example.hospitalbookingassignmet.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelClass {
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;
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

    public List<Data> getData() {
        return data;
    }


    public void setData(List<Data> data) {
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

        @SerializedName("hosID")
        @Expose
        private Integer hosID;
        @SerializedName("hosName")
        @Expose
        private String hosName;
        @SerializedName("hosEmail")
        @Expose
        private String hosEmail;

        public Integer getHosID() {
            return hosID;
        }

        public void setHosID(Integer hosID) {
            this.hosID = hosID;
        }

        public String getHosName() {
            return hosName;
        }

        public void setHosName(String hosName) {
            this.hosName = hosName;
        }

        public String getHosEmail() {
            return hosEmail;
        }

        public void setHosEmail(String hosEmail) {
            this.hosEmail = hosEmail;
        }

        @SerializedName("docName")
        @Expose
        private String docName;
        @SerializedName("docID")
        @Expose
        private Integer docID;
        @SerializedName("hosID")

        public String getDocName() {
            return docName;
        }

        public void setDocName(String docName) {
            this.docName = docName;
        }

        public Integer getDocID() {
            return docID;
        }

        public void setDocID(Integer docID) {
            this.docID = docID;
        }



    }

    }


