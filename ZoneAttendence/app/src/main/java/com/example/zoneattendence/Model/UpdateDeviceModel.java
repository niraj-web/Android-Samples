package com.example.zoneattendence.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UpdateDeviceModel {
    @SerializedName("data")
    @Expose
    private List<Object> data = null;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
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
}
