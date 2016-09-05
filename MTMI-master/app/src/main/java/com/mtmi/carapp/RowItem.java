package com.mtmi.carapp;

/**
 * Created by sametdundar on 03/09/16.
 */
public class RowItem {
    private String car_name;
    int car_pic;
    private String status;
    private String contactType;

    public RowItem(String car_name, int profile_pic_id, String status, String contactType) {
        this.car_name = car_name;
        this.car_pic = profile_pic_id;
        this.status = status;
        this.contactType = contactType;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public int getCar_pic() {
        return car_pic;
    }

    public void setProfile_pic_id(int profile_pic_id) {
        this.car_pic = profile_pic_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }
}
