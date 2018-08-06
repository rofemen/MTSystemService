package com.rofe.pojo;

import java.util.ArrayList;
import java.util.Date;

public class Share {
    private Long shareId;

    private Long sharePublisher;

    private Date shareTime;

    private String sharePic1;

    private String sharePic2;

    private String sharePic3;

    private String sharePic4;

    private Integer shareGood;

    private String shareExtra1;

    private String shareExtra2;

    private Integer shareExtra3;

    private Integer shareType;

    private String shareContent;
    
    private Student publisher;
    
    private ArrayList<ComentDetail> cms;
    
    public Student getPublisher() {
		return publisher;
	}

	public void setPublisher(Student publisher) {
		this.publisher = publisher;
	}

	public ArrayList<ComentDetail> getCms() {
		return cms;
	}

	public void setCms(ArrayList<ComentDetail> cms) {
		this.cms = cms;
	}

	public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Long getSharePublisher() {
        return sharePublisher;
    }

    public void setSharePublisher(Long sharePublisher) {
        this.sharePublisher = sharePublisher;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public String getSharePic1() {
        return sharePic1;
    }

    public void setSharePic1(String sharePic1) {
        this.sharePic1 = sharePic1 == null ? null : sharePic1.trim();
    }

    public String getSharePic2() {
        return sharePic2;
    }

    public void setSharePic2(String sharePic2) {
        this.sharePic2 = sharePic2 == null ? null : sharePic2.trim();
    }

    public String getSharePic3() {
        return sharePic3;
    }

    public void setSharePic3(String sharePic3) {
        this.sharePic3 = sharePic3 == null ? null : sharePic3.trim();
    }

    public String getSharePic4() {
        return sharePic4;
    }

    public void setSharePic4(String sharePic4) {
        this.sharePic4 = sharePic4 == null ? null : sharePic4.trim();
    }

    public Integer getShareGood() {
        return shareGood;
    }

    public void setShareGood(Integer shareGood) {
        this.shareGood = shareGood;
    }

    public String getShareExtra1() {
        return shareExtra1;
    }

    public void setShareExtra1(String shareExtra1) {
        this.shareExtra1 = shareExtra1 == null ? null : shareExtra1.trim();
    }

    public String getShareExtra2() {
        return shareExtra2;
    }

    public void setShareExtra2(String shareExtra2) {
        this.shareExtra2 = shareExtra2 == null ? null : shareExtra2.trim();
    }

    public Integer getShareExtra3() {
        return shareExtra3;
    }

    public void setShareExtra3(Integer shareExtra3) {
        this.shareExtra3 = shareExtra3;
    }

    public Integer getShareType() {
        return shareType;
    }

    public void setShareType(Integer shareType) {
        this.shareType = shareType;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent == null ? null : shareContent.trim();
    }
}