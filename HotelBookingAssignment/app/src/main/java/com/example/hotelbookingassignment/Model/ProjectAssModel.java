package com.example.hotelbookingassignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectAssModel {


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

    public class Data {

        @SerializedName("rewardDetails")
        @Expose
        private RewardDetails rewardDetails;
        @SerializedName("denomination")
        @Expose
        private List<Denomination> denomination = null;
        @SerializedName("location")
        @Expose
        private List<Location> location = null;

        public RewardDetails getRewardDetails() {
            return rewardDetails;
        }

        public void setRewardDetails(RewardDetails rewardDetails) {
            this.rewardDetails = rewardDetails;
        }

        public List<Denomination> getDenomination() {
            return denomination;
        }

        public void setDenomination(List<Denomination> denomination) {
            this.denomination = denomination;
        }

        public List<Location> getLocation() {
            return location;
        }

        public void setLocation(List<Location> location) {
            this.location = location;
        }

    }

    public class Denomination {

        @SerializedName("denomID")
        @Expose
        private Integer denomID;
        @SerializedName("vouchersID")
        @Expose
        private Integer vouchersID;
        @SerializedName("denomVoucherPrice")
        @Expose
        private String denomVoucherPrice;
        @SerializedName("denomPrice")
        @Expose
        private String denomPrice;
        @SerializedName("denomPoints")
        @Expose
        private String denomPoints;
        @SerializedName("denomUserQty")
        @Expose
        private String denomUserQty="0";
        @SerializedName("denomPendingQty")
        @Expose
        private Integer denomPendingQty;
        @SerializedName("dateAdded")
        @Expose
        private String dateAdded;
        @SerializedName("dateModified")
        @Expose
        private String dateModified;
        @SerializedName("status")
        @Expose
        private Integer status;

        public Integer getDenomID() {
            return denomID;
        }

        public void setDenomID(Integer denomID) {
            this.denomID = denomID;
        }

        public Integer getVouchersID() {
            return vouchersID;
        }

        public void setVouchersID(Integer vouchersID) {
            this.vouchersID = vouchersID;
        }

        public String getDenomVoucherPrice() {
            return denomVoucherPrice;
        }

        public void setDenomVoucherPrice(String denomVoucherPrice) {
            this.denomVoucherPrice = denomVoucherPrice;
        }

        public String getDenomPrice() {
            return denomPrice;
        }

        public void setDenomPrice(String denomPrice) {
            this.denomPrice = denomPrice;
        }

        public String getDenomPoints() {
            return denomPoints;
        }

        public void setDenomPoints(String denomPoints) {
            this.denomPoints = denomPoints;
        }



        public Integer getDenomPendingQty() {
            return denomPendingQty;
        }

        public void setDenomPendingQty(Integer denomPendingQty) {
            this.denomPendingQty = denomPendingQty;
        }

        public String getDateAdded() {
            return dateAdded;
        }

        public void setDateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
        }

        public String getDateModified() {
            return dateModified;
        }

        public void setDateModified(String dateModified) {
            this.dateModified = dateModified;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getDenomUserQty() {
            return denomUserQty;
        }

        public void setDenomUserQty(String denomUserQty) {
            this.denomUserQty = denomUserQty;
        }
    }

    public class Location {

        @SerializedName("outletAddress")
        @Expose
        private String outletAddress;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("outletContactNo")
        @Expose
        private String outletContactNo;

        @SerializedName("displayName")
        @Expose
        private String displayName;
        public String getOutletAddress() {
            return outletAddress;
        }

        public void setOutletAddress(String outletAddress) {
            this.outletAddress = outletAddress;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getOutletContactNo() {
            return outletContactNo;
        }

        public void setOutletContactNo(String outletContactNo) {
            this.outletContactNo = outletContactNo;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }
    }

    public class RewardDetails {

        @SerializedName("voucherID")
        @Expose
        private Integer voucherID;
        @SerializedName("merchantID")
        @Expose
        private Integer merchantID;
        @SerializedName("categoryTypeID")
        @Expose
        private Integer categoryTypeID;
        @SerializedName("categoryID")
        @Expose
        private Integer categoryID;
        @SerializedName("langCode")
        @Expose
        private String langCode;
        @SerializedName("categoryTitle")
        @Expose
        private String categoryTitle;
        @SerializedName("langChild")
        @Expose
        private Object langChild;
        @SerializedName("seoUri")
        @Expose
        private String seoUri;
        @SerializedName("tnc")
        @Expose
        private Integer tnc;
        @SerializedName("voucherTitle")
        @Expose
        private String voucherTitle;
        @SerializedName("voucherDescription")
        @Expose
        private String voucherDescription;
        @SerializedName("paymentType")
        @Expose
        private Integer paymentType;
        @SerializedName("voucherBrandDesc")
        @Expose
        private String voucherBrandDesc;
        @SerializedName("voucherFromDate")
        @Expose
        private String voucherFromDate;
        @SerializedName("voucherToDate")
        @Expose
        private String voucherToDate;
        @SerializedName("voucherImage")
        @Expose
        private String voucherImage;
        @SerializedName("dateAdded")
        @Expose
        private String dateAdded;
        @SerializedName("dateModified")
        @Expose
        private String dateModified;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("tncTitle")
        @Expose
        private String tncTitle;
        @SerializedName("tncDescription")
        @Expose
        private String tncDescription;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;

        public Integer getVoucherID() {
            return voucherID;
        }

        public void setVoucherID(Integer voucherID) {
            this.voucherID = voucherID;
        }

        public Integer getMerchantID() {
            return merchantID;
        }

        public void setMerchantID(Integer merchantID) {
            this.merchantID = merchantID;
        }

        public Integer getCategoryTypeID() {
            return categoryTypeID;
        }

        public void setCategoryTypeID(Integer categoryTypeID) {
            this.categoryTypeID = categoryTypeID;
        }

        public Integer getCategoryID() {
            return categoryID;
        }

        public void setCategoryID(Integer categoryID) {
            this.categoryID = categoryID;
        }

        public String getLangCode() {
            return langCode;
        }

        public void setLangCode(String langCode) {
            this.langCode = langCode;
        }

        public Object getLangChild() {
            return langChild;
        }

        public void setLangChild(Object langChild) {
            this.langChild = langChild;
        }

        public String getSeoUri() {
            return seoUri;
        }

        public void setSeoUri(String seoUri) {
            this.seoUri = seoUri;
        }

        public Integer getTnc() {
            return tnc;
        }

        public void setTnc(Integer tnc) {
            this.tnc = tnc;
        }

        public String getVoucherTitle() {
            return voucherTitle;
        }

        public void setVoucherTitle(String voucherTitle) {
            this.voucherTitle = voucherTitle;
        }

        public String getVoucherDescription() {
            return voucherDescription;
        }

        public void setVoucherDescription(String voucherDescription) {
            this.voucherDescription = voucherDescription;
        }

        public Integer getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(Integer paymentType) {
            this.paymentType = paymentType;
        }

        public String getVoucherBrandDesc() {
            return voucherBrandDesc;
        }

        public void setVoucherBrandDesc(String voucherBrandDesc) {
            this.voucherBrandDesc = voucherBrandDesc;
        }

        public String getVoucherFromDate() {
            return voucherFromDate;
        }

        public void setVoucherFromDate(String voucherFromDate) {
            this.voucherFromDate = voucherFromDate;
        }

        public String getVoucherToDate() {
            return voucherToDate;
        }

        public void setVoucherToDate(String voucherToDate) {
            this.voucherToDate = voucherToDate;
        }

        public String getVoucherImage() {
            return voucherImage;
        }

        public void setVoucherImage(String voucherImage) {
            this.voucherImage = voucherImage;
        }

        public String getDateAdded() {
            return dateAdded;
        }

        public void setDateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
        }

        public String getDateModified() {
            return dateModified;
        }

        public void setDateModified(String dateModified) {
            this.dateModified = dateModified;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getTncTitle() {
            return tncTitle;
        }

        public void setTncTitle(String tncTitle) {
            this.tncTitle = tncTitle;
        }

        public String getTncDescription() {
            return tncDescription;
        }

        public void setTncDescription(String tncDescription) {
            this.tncDescription = tncDescription;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getCategoryTitle() {
            return categoryTitle;
        }

        public void setCategoryTitle(String categoryTitle) {
            this.categoryTitle = categoryTitle;
        }
    }


}

