package com.example.ratingassignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectAssModel
{
        @SerializedName("msg")
        @Expose
        private String msg;
        @SerializedName("data")
        @Expose
        private Data data;
        @SerializedName("status")
        @Expose
        private Boolean status;

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

        public class Active {

            @SerializedName("userID")
            @Expose
            private Integer userID;
            @SerializedName("orderID")
            @Expose
            private Integer orderID;
            @SerializedName("productID")
            @Expose
            private Integer productID;
            @SerializedName("offerCouponCodeID")
            @Expose
            private Integer offerCouponCodeID;
            @SerializedName("orderType")
            @Expose
            private Integer orderType;
            @SerializedName("offerTitle")
            @Expose
            private String offerTitle;
            @SerializedName("offerToDate")
            @Expose
            private String offerToDate;
            @SerializedName("rating")
            @Expose
            private Integer rating;
            @SerializedName("ratingTitle")
            @Expose
            private String ratingTitle;
            @SerializedName("rateFor")
            @Expose
            private Integer rateFor;
            @SerializedName("ratingImage")
            @Expose
            private String ratingImage;

            public Integer getUserID() {
                return userID;
            }

            public void setUserID(Integer userID) {
                this.userID = userID;
            }

            public Integer getOrderID() {
                return orderID;
            }

            public void setOrderID(Integer orderID) {
                this.orderID = orderID;
            }

            public Integer getProductID() {
                return productID;
            }

            public void setProductID(Integer productID) {
                this.productID = productID;
            }

            public Integer getOfferCouponCodeID() {
                return offerCouponCodeID;
            }

            public void setOfferCouponCodeID(Integer offerCouponCodeID) {
                this.offerCouponCodeID = offerCouponCodeID;
            }

            public Integer getOrderType() {
                return orderType;
            }

            public void setOrderType(Integer orderType) {
                this.orderType = orderType;
            }

            public String getOfferTitle() {
                return offerTitle;
            }

            public void setOfferTitle(String offerTitle) {
                this.offerTitle = offerTitle;
            }

            public String getOfferToDate() {
                return offerToDate;
            }

            public void setOfferToDate(String offerToDate) {
                this.offerToDate = offerToDate;
            }

            public Integer getRating() {
                return rating;
            }

            public void setRating(Integer rating) {
                this.rating = rating;
            }

            public String getRatingTitle() {
                return ratingTitle;
            }

            public void setRatingTitle(String ratingTitle) {
                this.ratingTitle = ratingTitle;
            }

            public Integer getRateFor() {
                return rateFor;
            }

            public void setRateFor(Integer rateFor) {
                this.rateFor = rateFor;
            }

            public String getRatingImage() {
                return ratingImage;
            }

            public void setRatingImage(String ratingImage) {
                this.ratingImage = ratingImage;
            }

        }

        public class Data {

            @SerializedName("Active")
            @Expose
            private List<Active> active = null;
            @SerializedName("Expired")
            @Expose
            private List<Expired> expired = null;
            @SerializedName("Redeem")
            @Expose
            private List<Redeem> redeem = null;

            public List<Active> getActive() {
                return active;
            }

            public void setActive(List<Active> active) {
                this.active = active;
            }

            public List<Expired> getExpired() {
                return expired;
            }

            public void setExpired(List<Expired> expired) {
                this.expired = expired;
            }

            public List<Redeem> getRedeem() {
                return redeem;
            }

            public void setRedeem(List<Redeem> redeem) {
                this.redeem = redeem;
            }

        }
        public class Expired {

            @SerializedName("userID")
            @Expose
            private Integer userID;
            @SerializedName("orderID")
            @Expose
            private Integer orderID;
            @SerializedName("productID")
            @Expose
            private Integer productID;
            @SerializedName("offerCouponCodeID")
            @Expose
            private Integer offerCouponCodeID;
            @SerializedName("orderType")
            @Expose
            private Integer orderType;
            @SerializedName("offerTitle")
            @Expose
            private String offerTitle;
            @SerializedName("offerToDate")
            @Expose
            private String offerToDate;
            @SerializedName("rating")
            @Expose
            private Integer rating;
            @SerializedName("ratingTitle")
            @Expose
            private String ratingTitle;
            @SerializedName("rateFor")
            @Expose
            private Integer rateFor;
            @SerializedName("ratingImage")
            @Expose
            private String ratingImage;

            public Integer getUserID() {
                return userID;
            }

            public void setUserID(Integer userID) {
                this.userID = userID;
            }

            public Integer getOrderID() {
                return orderID;
            }

            public void setOrderID(Integer orderID) {
                this.orderID = orderID;
            }

            public Integer getProductID() {
                return productID;
            }

            public void setProductID(Integer productID) {
                this.productID = productID;
            }

            public Integer getOfferCouponCodeID() {
                return offerCouponCodeID;
            }

            public void setOfferCouponCodeID(Integer offerCouponCodeID) {
                this.offerCouponCodeID = offerCouponCodeID;
            }

            public Integer getOrderType() {
                return orderType;
            }

            public void setOrderType(Integer orderType) {
                this.orderType = orderType;
            }

            public String getOfferTitle() {
                return offerTitle;
            }

            public void setOfferTitle(String offerTitle) {
                this.offerTitle = offerTitle;
            }

            public String getOfferToDate() {
                return offerToDate;
            }

            public void setOfferToDate(String offerToDate) {
                this.offerToDate = offerToDate;
            }

            public Integer getRating() {
                return rating;
            }

            public void setRating(Integer rating) {
                this.rating = rating;
            }

            public String getRatingTitle() {
                return ratingTitle;
            }

            public void setRatingTitle(String ratingTitle) {
                this.ratingTitle = ratingTitle;
            }

            public Integer getRateFor() {
                return rateFor;
            }

            public void setRateFor(Integer rateFor) {
                this.rateFor = rateFor;
            }

            public String getRatingImage() {
                return ratingImage;
            }

            public void setRatingImage(String ratingImage) {
                this.ratingImage = ratingImage;
            }

        }

        public class Redeem {

            @SerializedName("userID")
            @Expose
            private Integer userID;
            @SerializedName("orderID")
            @Expose
            private Integer orderID;
            @SerializedName("productID")
            @Expose
            private Integer productID;
            @SerializedName("offerCouponCodeID")
            @Expose
            private Integer offerCouponCodeID;
            @SerializedName("orderType")
            @Expose
            private Integer orderType;
            @SerializedName("offerTitle")
            @Expose
            private String offerTitle;
            @SerializedName("offerToDate")
            @Expose
            private String offerToDate;
            @SerializedName("rating")
            @Expose
            private Integer rating;
            @SerializedName("ratingTitle")
            @Expose
            private String ratingTitle;
            @SerializedName("rateFor")
            @Expose
            private Integer rateFor;
            @SerializedName("ratingImage")
            @Expose
            private String ratingImage;

            public Integer getUserID() {
                return userID;
            }

            public void setUserID(Integer userID) {
                this.userID = userID;
            }

            public Integer getOrderID() {
                return orderID;
            }

            public void setOrderID(Integer orderID) {
                this.orderID = orderID;
            }

            public Integer getProductID() {
                return productID;
            }

            public void setProductID(Integer productID) {
                this.productID = productID;
            }

            public Integer getOfferCouponCodeID() {
                return offerCouponCodeID;
            }

            public void setOfferCouponCodeID(Integer offerCouponCodeID) {
                this.offerCouponCodeID = offerCouponCodeID;
            }

            public Integer getOrderType() {
                return orderType;
            }

            public void setOrderType(Integer orderType) {
                this.orderType = orderType;
            }

            public String getOfferTitle() {
                return offerTitle;
            }

            public void setOfferTitle(String offerTitle) {
                this.offerTitle = offerTitle;
            }

            public String getOfferToDate() {
                return offerToDate;
            }

            public void setOfferToDate(String offerToDate) {
                this.offerToDate = offerToDate;
            }

            public Integer getRating() {
                return rating;
            }

            public void setRating(Integer rating) {
                this.rating = rating;
            }

            public String getRatingTitle() {
                return ratingTitle;
            }

            public void setRatingTitle(String ratingTitle) {
                this.ratingTitle = ratingTitle;
            }

            public Integer getRateFor() {
                return rateFor;
            }

            public void setRateFor(Integer rateFor) {
                this.rateFor = rateFor;
            }

            public String getRatingImage() {
                return ratingImage;
            }

            public void setRatingImage(String ratingImage) {
                this.ratingImage = ratingImage;
            }

        }
    }

