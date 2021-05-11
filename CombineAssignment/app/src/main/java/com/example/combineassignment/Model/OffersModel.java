package com.example.combineassignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OffersModel {
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

        @SerializedName("totalOffers")
        @Expose
        private Integer totalOffers;
        @SerializedName("categoryID")
        @Expose
        private Integer categoryID;
        @SerializedName("categoryTypeID")
        @Expose
        private Integer categoryTypeID;
        @SerializedName("categoryTitle")
        @Expose
        private String categoryTitle;
        @SerializedName("imageName")
        @Expose
        private String imageName;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;

        public Integer getTotalOffers() {
            return totalOffers;
        }

        public void setTotalOffers(Integer totalOffers) {
            this.totalOffers = totalOffers;
        }

        public Integer getCategoryID() {
            return categoryID;
        }

        public void setCategoryID(Integer categoryID) {
            this.categoryID = categoryID;
        }

        public Integer getCategoryTypeID() {
            return categoryTypeID;
        }

        public void setCategoryTypeID(Integer categoryTypeID) {
            this.categoryTypeID = categoryTypeID;
        }

        public String getCategoryTitle() {
            return categoryTitle;
        }

        public void setCategoryTitle(String categoryTitle) {
            this.categoryTitle = categoryTitle;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

    }

}
