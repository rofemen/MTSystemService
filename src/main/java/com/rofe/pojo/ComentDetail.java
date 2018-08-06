package com.rofe.pojo;

import java.util.Date;

public class ComentDetail {
    private Integer cmId;

    private Long cmShareId;

    private Integer cmSendId;

    private Integer cmReceId;

    private String cmContent;

    private Date cmTime;

    private String cmExtra1;

    private String cmExtra2;
    
    private Student receer;
    
    private Student sender;
    
    public Student getReceer() {
		return receer;
	}

	public void setReceer(Student receer) {
		this.receer = receer;
	}

	public Student getSender() {
		return sender;
	}

	public void setSender(Student sender) {
		this.sender = sender;
	}

    public Integer getCmId() {
        return cmId;
    }

    public void setCmId(Integer cmId) {
        this.cmId = cmId;
    }

    public Long getCmShareId() {
        return cmShareId;
    }

    public void setCmShareId(Long cmShareId) {
        this.cmShareId = cmShareId;
    }

    public Integer getCmSendId() {
        return cmSendId;
    }

    public void setCmSendId(Integer cmSendId) {
        this.cmSendId = cmSendId;
    }

    public Integer getCmReceId() {
        return cmReceId;
    }

    public void setCmReceId(Integer cmReceId) {
        this.cmReceId = cmReceId;
    }

    public String getCmContent() {
        return cmContent;
    }

    public void setCmContent(String cmContent) {
        this.cmContent = cmContent == null ? null : cmContent.trim();
    }

    public Date getCmTime() {
        return cmTime;
    }

    public void setCmTime(Date cmTime) {
        this.cmTime = cmTime;
    }

    public String getCmExtra1() {
        return cmExtra1;
    }

    public void setCmExtra1(String cmExtra1) {
        this.cmExtra1 = cmExtra1 == null ? null : cmExtra1.trim();
    }

    public String getCmExtra2() {
        return cmExtra2;
    }

    public void setCmExtra2(String cmExtra2) {
        this.cmExtra2 = cmExtra2 == null ? null : cmExtra2.trim();
    }
}