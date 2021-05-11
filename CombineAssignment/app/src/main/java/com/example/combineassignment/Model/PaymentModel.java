package com.example.combineassignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentModel {
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

        @SerializedName("offerID")
        @Expose
        private String offerID;
        @SerializedName("totalAmount")
        @Expose
        private String totalAmount;
        @SerializedName("subTotal")
        @Expose
        private String subTotal;
        @SerializedName("orderPoint")
        @Expose
        private Integer orderPoint;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("point")
        @Expose
        private String point;
        @SerializedName("itemVatPrice")
        @Expose
        private String itemVatPrice;
        @SerializedName("itemVatPoint")
        @Expose
        private String itemVatPoint;
        @SerializedName("totalVatPrice")
        @Expose
        private String totalVatPrice;
        @SerializedName("totalVatPoint")
        @Expose
        private Integer totalVatPoint;
        @SerializedName("offerExpiryDate")
        @Expose
        private String offerExpiryDate;
        @SerializedName("pointsConverstion")
        @Expose
        private PointsConverstion pointsConverstion;

        public String getOfferID() {
            return offerID;
        }

        public void setOfferID(String offerID) {
            this.offerID = offerID;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getSubTotal() {
            return subTotal;
        }

        public void setSubTotal(String subTotal) {
            this.subTotal = subTotal;
        }

        public Integer getOrderPoint() {
            return orderPoint;
        }

        public void setOrderPoint(Integer orderPoint) {
            this.orderPoint = orderPoint;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getItemVatPrice() {
            return itemVatPrice;
        }

        public void setItemVatPrice(String itemVatPrice) {
            this.itemVatPrice = itemVatPrice;
        }

        public String getItemVatPoint() {
            return itemVatPoint;
        }

        public void setItemVatPoint(String itemVatPoint) {
            this.itemVatPoint = itemVatPoint;
        }

        public String getTotalVatPrice() {
            return totalVatPrice;
        }

        public void setTotalVatPrice(String totalVatPrice) {
            this.totalVatPrice = totalVatPrice;
        }

        public Integer getTotalVatPoint() {
            return totalVatPoint;
        }

        public void setTotalVatPoint(Integer totalVatPoint) {
            this.totalVatPoint = totalVatPoint;
        }

        public String getOfferExpiryDate() {
            return offerExpiryDate;
        }

        public void setOfferExpiryDate(String offerExpiryDate) {
            this.offerExpiryDate = offerExpiryDate;
        }

        public PointsConverstion getPointsConverstion() {
            return pointsConverstion;
        }

        public void setPointsConverstion(PointsConverstion pointsConverstion) {
            this.pointsConverstion = pointsConverstion;
        }

    }

    public class PointsConverstion {

        @SerializedName("categoryTypeID")
        @Expose
        private Integer categoryTypeID;
        @SerializedName("categoryTypeEarnPrice")
        @Expose
        private Integer categoryTypeEarnPrice;
        @SerializedName("categoryTypeEarnPoints")
        @Expose
        private Integer categoryTypeEarnPoints;
        @SerializedName("categoryTypeRedeemPrice")
        @Expose
        private Integer categoryTypeRedeemPrice;
        @SerializedName("categoryTypeRedeemPoints")
        @Expose
        private Integer categoryTypeRedeemPoints;

        public Integer getCategoryTypeID() {
            return categoryTypeID;
        }

        public void setCategoryTypeID(Integer categoryTypeID) {
            this.categoryTypeID = categoryTypeID;
        }

        public Integer getCategoryTypeEarnPrice() {
            return categoryTypeEarnPrice;
        }

        public void setCategoryTypeEarnPrice(Integer categoryTypeEarnPrice) {
            this.categoryTypeEarnPrice = categoryTypeEarnPrice;
        }

        public Integer getCategoryTypeEarnPoints() {
            return categoryTypeEarnPoints;
        }

        public void setCategoryTypeEarnPoints(Integer categoryTypeEarnPoints) {
            this.categoryTypeEarnPoints = categoryTypeEarnPoints;
        }

        public Integer getCategoryTypeRedeemPrice() {
            return categoryTypeRedeemPrice;
        }

        public void setCategoryTypeRedeemPrice(Integer categoryTypeRedeemPrice) {
            this.categoryTypeRedeemPrice = categoryTypeRedeemPrice;
        }

        public Integer getCategoryTypeRedeemPoints() {
            return categoryTypeRedeemPoints;
        }

        public void setCategoryTypeRedeemPoints(Integer categoryTypeRedeemPoints) {
            this.categoryTypeRedeemPoints = categoryTypeRedeemPoints;
        }

    }
}
