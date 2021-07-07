package com.example.zoneattendence.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminBarcodeModel {
    @SerializedName("admin_barcode_list")
    @Expose
    private List<AdminBarcode> adminBarcodeList = null;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<AdminBarcode> getAdminBarcodeList() {
        return adminBarcodeList;
    }

    public void setAdminBarcodeList(List<AdminBarcode> adminBarcodeList) {
        this.adminBarcodeList = adminBarcodeList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public class AdminBarcode {

        @SerializedName("barcodeID")
        @Expose
        private Integer barcodeID;
        @SerializedName("barcodeNumber")
        @Expose
        private String barcodeNumber;
        @SerializedName("customerName")
        @Expose
        private String customerName;
        @SerializedName("attStatus")
        @Expose
        private Integer attStatus;

        public Integer getBarcodeID() {
            return barcodeID;
        }

        public void setBarcodeID(Integer barcodeID) {
            this.barcodeID = barcodeID;
        }

        public String getBarcodeNumber() {
            return barcodeNumber;
        }

        public void setBarcodeNumber(String barcodeNumber) {
            this.barcodeNumber = barcodeNumber;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public Integer getAttStatus() {
            return attStatus;
        }

        public void setAttStatus(Integer attStatus) {
            this.attStatus = attStatus;
        }
    }
}
