package com.example.fragmentassignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DentalModel {
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

        @SerializedName("minDistance")
        @Expose
        private String minDistance;
        @SerializedName("offerID")
        @Expose
        private Integer offerID;
        @SerializedName("merchantID")
        @Expose
        private Integer merchantID;
        @SerializedName("tncID")
        @Expose
        private Object tncID;
        @SerializedName("offerTitle")
        @Expose
        private String offerTitle;
        @SerializedName("offerTagline")
        @Expose
        private String offerTagline;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("point")
        @Expose
        private Integer point;
        @SerializedName("offerDiscount")
        @Expose
        private String offerDiscount;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("offerImage")
        @Expose
        private String offerImage;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;

        public String getMinDistance() {
            return minDistance;
        }

        public void setMinDistance(String minDistance) {
            this.minDistance = minDistance;
        }

        public Integer getOfferID() {
            return offerID;
        }

        public void setOfferID(Integer offerID) {
            this.offerID = offerID;
        }

        public Integer getMerchantID() {
            return merchantID;
        }

        public void setMerchantID(Integer merchantID) {
            this.merchantID = merchantID;
        }

        public Object getTncID() {
            return tncID;
        }

        public void setTncID(Object tncID) {
            this.tncID = tncID;
        }

        public String getOfferTitle() {
            return offerTitle;
        }

        public void setOfferTitle(String offerTitle) {
            this.offerTitle = offerTitle;
        }

        public String getOfferTagline() {
            return offerTagline;
        }

        public void setOfferTagline(String offerTagline) {
            this.offerTagline = offerTagline;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Integer getPoint() {
            return point;
        }

        public void setPoint(Integer point) {
            this.point = point;
        }

        public String getOfferDiscount() {
            return offerDiscount;
        }

        public void setOfferDiscount(String offerDiscount) {
            this.offerDiscount = offerDiscount;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public String getOfferImage() {
            return offerImage;
        }

        public void setOfferImage(String offerImage) {
            this.offerImage = offerImage;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

    }

}
