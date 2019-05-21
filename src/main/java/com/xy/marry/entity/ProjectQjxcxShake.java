package com.xy.marry.entity;

import java.util.Date;

public class ProjectQjxcxShake {
    private Integer id;

    private Integer qjxcxId;

    private Integer participants;

    private Date addTime;

    private Date updateTime;

    private Byte isDelete;

    private Byte state;
    
    private Double shakeRange;
    
    private Integer countDown;
    
    private String roomName;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQjxcxId() {
        return qjxcxId;
    }

    public void setQjxcxId(Integer qjxcxId) {
        this.qjxcxId = qjxcxId;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
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

	public Double getShakeRange() {
		return shakeRange;
	}

	public void setShakeRange(Double shakeRange) {
		this.shakeRange = shakeRange;
	}

	public Integer getCountDown() {
		return countDown;
	}

	public void setCountDown(Integer countDown) {
		this.countDown = countDown;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
    
}