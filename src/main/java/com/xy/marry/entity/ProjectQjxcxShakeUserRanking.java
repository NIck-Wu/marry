package com.xy.marry.entity;

import java.util.Date;

public class ProjectQjxcxShakeUserRanking {
    private Integer id;

    private Integer shakeId;

    private Integer qjxcxId;

    private Integer userId;

    private Integer ranking;

    private Integer number;

    private Date addTime;

    private Date updateTime;

    private Byte isDelete;

    private Byte state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShakeId() {
        return shakeId;
    }

    public void setShakeId(Integer shakeId) {
        this.shakeId = shakeId;
    }

    public Integer getQjxcxId() {
        return qjxcxId;
    }

    public void setQjxcxId(Integer qjxcxId) {
        this.qjxcxId = qjxcxId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}