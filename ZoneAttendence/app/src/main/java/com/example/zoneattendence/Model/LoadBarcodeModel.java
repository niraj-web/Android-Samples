package com.example.zoneattendence.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadBarcodeModel
{
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
