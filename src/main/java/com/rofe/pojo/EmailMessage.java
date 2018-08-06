package com.rofe.pojo;

import java.util.Date;

public class EmailMessage {
    private Integer emId;

    private Date emTime;

    private Integer emSender;

    private Integer emReceiver;

    private String emType;

    private String emTheme;

    private String emExtra1;

    private Integer emExtra2;

    private String emContent;
    
    private String senderName;
    
    private String receiverName;
    
    

    public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public Date getEmTime() {
        return emTime;
    }

    public void setEmTime(Date emTime) {
        this.emTime = emTime;
    }

    public Integer getEmSender() {
        return emSender;
    }

    public void setEmSender(Integer emSender) {
        this.emSender = emSender;
    }

    public Integer getEmReceiver() {
        return emReceiver;
    }

    public void setEmReceiver(Integer emReceiver) {
        this.emReceiver = emReceiver;
    }

    public String getEmType() {
        return emType;
    }

    public void setEmType(String emType) {
        this.emType = emType == null ? null : emType.trim();
    }

    public String getEmTheme() {
        return emTheme;
    }

    public void setEmTheme(String emTheme) {
        this.emTheme = emTheme == null ? null : emTheme.trim();
    }

    public String getEmExtra1() {
        return emExtra1;
    }

    public void setEmExtra1(String emExtra1) {
        this.emExtra1 = emExtra1 == null ? null : emExtra1.trim();
    }

    public Integer getEmExtra2() {
        return emExtra2;
    }

    public void setEmExtra2(Integer emExtra2) {
        this.emExtra2 = emExtra2;
    }

    public String getEmContent() {
        return emContent;
    }

    public void setEmContent(String emContent) {
        this.emContent = emContent == null ? null : emContent.trim();
    }
}