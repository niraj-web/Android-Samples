package com.example.combineassignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HospitalModel {

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
        @SerializedName("subcategoryID")
        @Expose
        private Integer subcategoryID;
        @SerializedName("categoryID")
        @Expose
        private Object categoryID;
        @SerializedName("categoryTypeID")
        @Expose
        private Integer categoryTypeID;
        @SerializedName("subcategoryTitle")
        @Expose
        private String subcategoryTitle;
        @SerializedName("bannerImage")
        @Expose
        private String bannerImage;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;

        public Integer getTotalOffers() {
            return totalOffers;
        }

        public void setTotalOffers(Integer totalOffers) {
            this.totalOffers = totalOffers;
        }

        public Integer getSubcategoryID() {
            return subcategoryID;
        }

        public void setSubcategoryID(Integer subcategoryID) {
            this.subcategoryID = subcategoryID;
        }

        public Object getCategoryID() {
            return categoryID;
        }

        public void setCategoryID(Object categoryID) {
            this.categoryID = categoryID;
        }

        public Integer getCategoryTypeID() {
            return categoryTypeID;
        }

        public void setCategoryTypeID(Integer categoryTypeID) {
            this.categoryTypeID = categoryTypeID;
        }

        public String getSubcategoryTitle() {
            return subcategoryTitle;
        }

        public void setSubcategoryTitle(String subcategoryTitle) {
            this.subcategoryTitle = subcategoryTitle;
        }

        public String getBannerImage() {
            return bannerImage;
        }

        public void setBannerImage(String bannerImage) {
            this.bannerImage = bannerImage;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

    }
}
