package com.xy.marry.entity;

import java.util.Date;

public class ProjectQjxcx {
    private Integer id;

    private Integer merchantId;

    private Integer merchantProjectId;

    private String heName;

    private String sheName;

    private String heTel;

    private String sheTel;

    private String marryDate;

    private String marryDateLunar;

    private String cover;

    private Integer musicId;
    
    private String musicUrl;

    private String hotel;

    private String address;

    private Double lat;

    private Double lng;

    private String share;

    private String shareThumb;

    private String xcxImg;

    private Date addTime;

    private Date updateTime;

    private Byte state;

    private Byte isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMerchantProjectId() {
        return merchantProjectId;
    }

    public void setMerchantProjectId(Integer merchantProjectId) {
        this.merchantProjectId = merchantProjectId;
    }

    public String getHeName() {
        return heName;
    }

    public void setHeName(String heName) {
        this.heName = heName == null ? null : heName.trim();
    }

    public String getSheName() {
        return sheName;
    }

    public void setSheName(String sheName) {
        this.sheName = sheName == null ? null : sheName.trim();
    }

    public String getHeTel() {
        return heTel;
    }

    public void setHeTel(String heTel) {
        this.heTel = heTel == null ? null : heTel.trim();
    }

    public String getSheTel() {
        return sheTel;
    }

    public void setSheTel(String sheTel) {
        this.sheTel = sheTel == null ? null : sheTel.trim();
    }

    public String getMarryDate() {
        return marryDate;
    }

    public void setMarryDate(String marryDate) {
        this.marryDate = marryDate == null ? null : marryDate.trim();
    }

    public String getMarryDateLunar() {
        return marryDateLunar;
    }

    public void setMarryDateLunar(String marryDateLunar) {
        this.marryDateLunar = marryDateLunar == null ? null : marryDateLunar.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel == null ? null : hotel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share == null ? null : share.trim();
    }

    public String getShareThumb() {
        return shareThumb;
    }

    public void setShareThumb(String shareThumb) {
        this.shareThumb = shareThumb == null ? null : shareThumb.trim();
    }

    public String getXcxImg() {
        return xcxImg;
    }

    public void setXcxImg(String xcxImg) {
        this.xcxImg = xcxImg == null ? null : xcxImg.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}