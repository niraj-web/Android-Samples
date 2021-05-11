package com.example.combineassignment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RoomDetailsModel implements Serializable {
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;
    @SerializedName("status")
    @Expose
    private Boolean status;

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

    public class Data implements Serializable{

        @SerializedName("roomID")
        @Expose
        private String roomID;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("total_rooms")
        @Expose
        private String totalRooms;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("bed_type")
        @Expose
        private String bedType;
        @SerializedName("buy_points")
        @Expose
        private Integer buyPoints;
        @SerializedName("location_latitude")
        @Expose
        private String locationLatitude;
        @SerializedName("location_longitude")
        @Expose
        private String locationLongitude;
        @SerializedName("checkin_time")
        @Expose
        private String checkinTime;
        @SerializedName("checkout_time")
        @Expose
        private String checkoutTime;
        @SerializedName("term&conditions")
        @Expose
        private String termConditions;
        @SerializedName("amenities_data")
        @Expose
        private List<AmenitiesDatum> amenitiesData = null;
        @SerializedName("gallery_data")
        @Expose
        private List<String> galleryData = null;
        @SerializedName("total_guest")
        @Expose
        private TotalGuest totalGuest;

        public String getRoomID() {
            return roomID;
        }

        public void setRoomID(String roomID) {
            this.roomID = roomID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTotalRooms() {
            return totalRooms;
        }

        public void setTotalRooms(String totalRooms) {
            this.totalRooms = totalRooms;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getBedType() {
            return bedType;
        }

        public void setBedType(String bedType) {
            this.bedType = bedType;
        }

        public Integer getBuyPoints() {
            return buyPoints;
        }

        public void setBuyPoints(Integer buyPoints) {
            this.buyPoints = buyPoints;
        }

        public String getLocationLatitude() {
            return locationLatitude;
        }

        public void setLocationLatitude(String locationLatitude) {
            this.locationLatitude = locationLatitude;
        }

        public String getLocationLongitude() {
            return locationLongitude;
        }

        public void setLocationLongitude(String locationLongitude) {
            this.locationLongitude = locationLongitude;
        }

        public String getCheckinTime() {
            return checkinTime;
        }

        public void setCheckinTime(String checkinTime) {
            this.checkinTime = checkinTime;
        }

        public String getCheckoutTime() {
            return checkoutTime;
        }

        public void setCheckoutTime(String checkoutTime) {
            this.checkoutTime = checkoutTime;
        }

        public String getTermConditions() {
            return termConditions;
        }

        public void setTermConditions(String termConditions) {
            this.termConditions = termConditions;
        }

        public List<AmenitiesDatum> getAmenitiesData() {
            return amenitiesData;
        }

        public void setAmenitiesData(List<AmenitiesDatum> amenitiesData) {
            this.amenitiesData = amenitiesData;
        }

        public List<String> getGalleryData() {
            return galleryData;
        }

        public void setGalleryData(List<String> galleryData) {
            this.galleryData = galleryData;
        }

        public TotalGuest getTotalGuest() {
            return totalGuest;
        }

        public void setTotalGuest(TotalGuest totalGuest) {
            this.totalGuest = totalGuest;
        }

    }

    public class TotalGuest implements Serializable{

        @SerializedName("adultID")
        @Expose
        private Integer adultID;
        @SerializedName("childID")
        @Expose
        private Integer childID;
        @SerializedName("adult-max")
        @Expose
        private String adultMax;
        @SerializedName("adult-min")
        @Expose
        private String adultMin;
        @SerializedName("child-max")
        @Expose
        private String childMax;
        @SerializedName("child-min")
        @Expose
        private String childMin;
        @SerializedName("total-min")
        @Expose
        private String totalMin;
        @SerializedName("total-max")
        @Expose
        private String totalMax;

        public Integer getAdultID() {
            return adultID;
        }

        public void setAdultID(Integer adultID) {
            this.adultID = adultID;
        }

        public Integer getChildID() {
            return childID;
        }

        public void setChildID(Integer childID) {
            this.childID = childID;
        }

        public String getAdultMax() {
            return adultMax;
        }

        public void setAdultMax(String adultMax) {
            this.adultMax = adultMax;
        }

        public String getAdultMin() {
            return adultMin;
        }

        public void setAdultMin(String adultMin) {
            this.adultMin = adultMin;
        }

        public String getChildMax() {
            return childMax;
        }

        public void setChildMax(String childMax) {
            this.childMax = childMax;
        }

        public String getChildMin() {
            return childMin;
        }

        public void setChildMin(String childMin) {
            this.childMin = childMin;
        }

        public String getTotalMin() {
            return totalMin;
        }

        public void setTotalMin(String totalMin) {
            this.totalMin = totalMin;
        }

        public String getTotalMax() {
            return totalMax;
        }

        public void setTotalMax(String totalMax) {
            this.totalMax = totalMax;
        }

    }

    public class AmenitiesDatum implements Serializable {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("icon")
        @Expose
        private String icon;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

    }

}

