package com.mtmi.carapp;

import android.media.Image;

/**
 * Created by mobiltek10 on 9/7/16.
 */
public class Araba {
    private Integer aracID;
    private String aracModel;
    private String aracMarka;
    private String aracPlaka;
    private String aracTrafikTrh;
    private String aracMuaTrh;
    private String aracSigTrh;
    private Image aracPic;

    public Araba(String aracModel, String aracMarka, String aracPlaka, String aracTrafikTrh, String aracMuaTrh, String aracSigTrh) {
        this.aracModel = aracModel;
        this.aracMarka = aracMarka;
        this.aracPlaka = aracPlaka;
        this.aracTrafikTrh = aracTrafikTrh;
        this.aracMuaTrh = aracMuaTrh;
        this.aracSigTrh = aracSigTrh;
    }

    public Integer getAracID() {
        return aracID;
    }

    public void setAracID(Integer aracID) {
        this.aracID = aracID;
    }

    public String getAracModel() {
        return aracModel;
    }

    public void setAracModel(String aracModel) {
        this.aracModel = aracModel;
    }

    public String getAracMarka() {
        return aracMarka;
    }

    public void setAracMarka(String aracMarka) {
        this.aracMarka = aracMarka;
    }

    public String getAracPlaka() {
        return aracPlaka;
    }

    public void setAracPlaka(String aracPlaka) {
        this.aracPlaka = aracPlaka;
    }

    public String getAracTrafikTrh() {
        return aracTrafikTrh;
    }

    public void setAracTrafikTrh(String aracTrafikTrh) {
        this.aracTrafikTrh = aracTrafikTrh;
    }

    public String getAracMuaTrh() {
        return aracMuaTrh;
    }

    public void setAracMuaTrh(String aracMuaTrh) {
        this.aracMuaTrh = aracMuaTrh;
    }

    public String getAracSigTrh() {
        return aracSigTrh;
    }

    public void setAracSigTrh(String aracSigTrh) {
        this.aracSigTrh = aracSigTrh;
    }

    public Image getAracPic() {
        return aracPic;
    }

    public void setAracPic(Image aracPic) {
        this.aracPic = aracPic;
    }
}
