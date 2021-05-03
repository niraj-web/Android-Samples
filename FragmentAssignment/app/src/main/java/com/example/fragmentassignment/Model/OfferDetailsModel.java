package com.example.fragmentassignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OfferDetailsModel {

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

        @SerializedName("offerDetails")
        @Expose
        private OfferDetails offerDetails;
        @SerializedName("location")
        @Expose
        private List<Object> location = null;
        @SerializedName("pointsConverstion")
        @Expose
        private PointsConverstion pointsConverstion;

        public OfferDetails getOfferDetails() {
            return offerDetails;
        }

        public void setOfferDetails(OfferDetails offerDetails) {
            this.offerDetails = offerDetails;
        }

        public List<Object> getLocation() {
            return location;
        }

        public void setLocation(List<Object> location) {
            this.location = location;
        }

        public PointsConverstion getPointsConverstion() {
            return pointsConverstion;
        }

        public void setPointsConverstion(PointsConverstion pointsConverstion) {
            this.pointsConverstion = pointsConverstion;
        }

    }

    public class OfferDetails {

        @SerializedName("offerID")
        @Expose
        private Integer offerID;
        @SerializedName("categoryTypeID")
        @Expose
        private Integer categoryTypeID;
        @SerializedName("categoryID")
        @Expose
        private Integer categoryID;
        @SerializedName("subcategoryID")
        @Expose
        private Integer subcategoryID;
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
        @SerializedName("offerDescription")
        @Expose
        private String offerDescription;
        @SerializedName("offerBrandDesc")
        @Expose
        private String offerBrandDesc;
        @SerializedName("tncDescription")
        @Expose
        private String tncDescription;
        @SerializedName("offerTitleAr")
        @Expose
        private String offerTitleAr;
        @SerializedName("offerTaglineAr")
        @Expose
        private String offerTaglineAr;
        @SerializedName("offerDescriptionAr")
        @Expose
        private String offerDescriptionAr;
        @SerializedName("offerBrandDescAr")
        @Expose
        private String offerBrandDescAr;
        @SerializedName("tncDescriptionAr")
        @Expose
        private String tncDescriptionAr;
        @SerializedName("isScratchWin")
        @Expose
        private Integer isScratchWin;
        @SerializedName("paymentType")
        @Expose
        private Integer paymentType;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("point")
        @Expose
        private Integer point;
        @SerializedName("vatPrice")
        @Expose
        private String vatPrice;
        @SerializedName("vatPoint")
        @Expose
        private Integer vatPoint;
        @SerializedName("offerFromDate")
        @Expose
        private String offerFromDate;
        @SerializedName("offerToDate")
        @Expose
        private String offerToDate;
        @SerializedName("offerDays")
        @Expose
        private Integer offerDays;
        @SerializedName("couponType")
        @Expose
        private Integer couponType;
        @SerializedName("quantity")
        @Expose
        private Object quantity;
        @SerializedName("balanceQTY")
        @Expose
        private Integer balanceQTY;
        @SerializedName("offerDiscount")
        @Expose
        private String offerDiscount;
        @SerializedName("voucherExpireType")
        @Expose
        private Integer voucherExpireType;
        @SerializedName("voucherExpireDays")
        @Expose
        private Integer voucherExpireDays;
        @SerializedName("voucherExpireDate")
        @Expose
        private String voucherExpireDate;
        @SerializedName("isImport")
        @Expose
        private Integer isImport;
        @SerializedName("isActive")
        @Expose
        private Integer isActive;
        @SerializedName("importFile")
        @Expose
        private Object importFile;
        @SerializedName("offerImage")
        @Expose
        private String offerImage;
        @SerializedName("offerThumbnail")
        @Expose
        private String offerThumbnail;
        @SerializedName("totalNotifyUsers")
        @Expose
        private Integer totalNotifyUsers;
        @SerializedName("sendNotifyUsers")
        @Expose
        private Integer sendNotifyUsers;
        @SerializedName("estimatedSaving")
        @Expose
        private String estimatedSaving;
        @SerializedName("purchaseCount")
        @Expose
        private Integer purchaseCount;
        @SerializedName("dateAdded")
        @Expose
        private String dateAdded;
        @SerializedName("dateModified")
        @Expose
        private String dateModified;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("categoryTitle")
        @Expose
        private String categoryTitle;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;

        public Integer getOfferID() {
            return offerID;
        }

        public void setOfferID(Integer offerID) {
            this.offerID = offerID;
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

        public Integer getSubcategoryID() {
            return subcategoryID;
        }

        public void setSubcategoryID(Integer subcategoryID) {
            this.subcategoryID = subcategoryID;
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

        public String getOfferDescription() {
            return offerDescription;
        }

        public void setOfferDescription(String offerDescription) {
            this.offerDescription = offerDescription;
        }

        public String getOfferBrandDesc() {
            return offerBrandDesc;
        }

        public void setOfferBrandDesc(String offerBrandDesc) {
            this.offerBrandDesc = offerBrandDesc;
        }

        public String getTncDescription() {
            return tncDescription;
        }

        public void setTncDescription(String tncDescription) {
            this.tncDescription = tncDescription;
        }

        public String getOfferTitleAr() {
            return offerTitleAr;
        }

        public void setOfferTitleAr(String offerTitleAr) {
            this.offerTitleAr = offerTitleAr;
        }

        public String getOfferTaglineAr() {
            return offerTaglineAr;
        }

        public void setOfferTaglineAr(String offerTaglineAr) {
            this.offerTaglineAr = offerTaglineAr;
        }

        public String getOfferDescriptionAr() {
            return offerDescriptionAr;
        }

        public void setOfferDescriptionAr(String offerDescriptionAr) {
            this.offerDescriptionAr = offerDescriptionAr;
        }

        public String getOfferBrandDescAr() {
            return offerBrandDescAr;
        }

        public void setOfferBrandDescAr(String offerBrandDescAr) {
            this.offerBrandDescAr = offerBrandDescAr;
        }

        public String getTncDescriptionAr() {
            return tncDescriptionAr;
        }

        public void setTncDescriptionAr(String tncDescriptionAr) {
            this.tncDescriptionAr = tncDescriptionAr;
        }

        public Integer getIsScratchWin() {
            return isScratchWin;
        }

        public void setIsScratchWin(Integer isScratchWin) {
            this.isScratchWin = isScratchWin;
        }

        public Integer getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(Integer paymentType) {
            this.paymentType = paymentType;
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

        public String getVatPrice() {
            return vatPrice;
        }

        public void setVatPrice(String vatPrice) {
            this.vatPrice = vatPrice;
        }

        public Integer getVatPoint() {
            return vatPoint;
        }

        public void setVatPoint(Integer vatPoint) {
            this.vatPoint = vatPoint;
        }

        public String getOfferFromDate() {
            return offerFromDate;
        }

        public void setOfferFromDate(String offerFromDate) {
            this.offerFromDate = offerFromDate;
        }

        public String getOfferToDate() {
            return offerToDate;
        }

        public void setOfferToDate(String offerToDate) {
            this.offerToDate = offerToDate;
        }

        public Integer getOfferDays() {
            return offerDays;
        }

        public void setOfferDays(Integer offerDays) {
            this.offerDays = offerDays;
        }

        public Integer getCouponType() {
            return couponType;
        }

        public void setCouponType(Integer couponType) {
            this.couponType = couponType;
        }

        public Object getQuantity() {
            return quantity;
        }

        public void setQuantity(Object quantity) {
            this.quantity = quantity;
        }

        public Integer getBalanceQTY() {
            return balanceQTY;
        }

        public void setBalanceQTY(Integer balanceQTY) {
            this.balanceQTY = balanceQTY;
        }

        public String getOfferDiscount() {
            return offerDiscount;
        }

        public void setOfferDiscount(String offerDiscount) {
            this.offerDiscount = offerDiscount;
        }

        public Integer getVoucherExpireType() {
            return voucherExpireType;
        }

        public void setVoucherExpireType(Integer voucherExpireType) {
            this.voucherExpireType = voucherExpireType;
        }

        public Integer getVoucherExpireDays() {
            return voucherExpireDays;
        }

        public void setVoucherExpireDays(Integer voucherExpireDays) {
            this.voucherExpireDays = voucherExpireDays;
        }

        public String getVoucherExpireDate() {
            return voucherExpireDate;
        }

        public void setVoucherExpireDate(String voucherExpireDate) {
            this.voucherExpireDate = voucherExpireDate;
        }

        public Integer getIsImport() {
            return isImport;
        }

        public void setIsImport(Integer isImport) {
            this.isImport = isImport;
        }

        public Integer getIsActive() {
            return isActive;
        }

        public void setIsActive(Integer isActive) {
            this.isActive = isActive;
        }

        public Object getImportFile() {
            return importFile;
        }

        public void setImportFile(Object importFile) {
            this.importFile = importFile;
        }

        public String getOfferImage() {
            return offerImage;
        }

        public void setOfferImage(String offerImage) {
            this.offerImage = offerImage;
        }

        public String getOfferThumbnail() {
            return offerThumbnail;
        }

        public void setOfferThumbnail(String offerThumbnail) {
            this.offerThumbnail = offerThumbnail;
        }

        public Integer getTotalNotifyUsers() {
            return totalNotifyUsers;
        }

        public void setTotalNotifyUsers(Integer totalNotifyUsers) {
            this.totalNotifyUsers = totalNotifyUsers;
        }

        public Integer getSendNotifyUsers() {
            return sendNotifyUsers;
        }

        public void setSendNotifyUsers(Integer sendNotifyUsers) {
            this.sendNotifyUsers = sendNotifyUsers;
        }

        public String getEstimatedSaving() {
            return estimatedSaving;
        }

        public void setEstimatedSaving(String estimatedSaving) {
            this.estimatedSaving = estimatedSaving;
        }

        public Integer getPurchaseCount() {
            return purchaseCount;
        }

        public void setPurchaseCount(Integer purchaseCount) {
            this.purchaseCount = purchaseCount;
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

        public String getCategoryTitle() {
            return categoryTitle;
        }

        public void setCategoryTitle(String categoryTitle) {
            this.categoryTitle = categoryTitle;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
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
