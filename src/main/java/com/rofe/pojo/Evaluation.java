package com.rofe.pojo;

import java.util.Date;

public class Evaluation {
    private Long eiId;

    private Long eiPublisher;

    private Long eiTarget;

    private Date eiTime;

    private Integer eiScore;

    private String eiExtra1;

    private String eiExtra2;

    private Integer eiCiCode;

    private String eiContent;

    private Student publisher;
    
    private Teacher target;
    
    private Course course;
    
    public Student getPublisher() {
		return publisher;
	}

	public void setPublisher(Student publisher) {
		this.publisher = publisher;
	}

	public Teacher getTarget() {
		return target;
	}

	public void setTarget(Teacher target) {
		this.target = target;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Long getEiId() {
        return eiId;
    }

    public void setEiId(Long eiId) {
        this.eiId = eiId;
    }

    public Long getEiPublisher() {
        return eiPublisher;
    }

    public void setEiPublisher(Long eiPublisher) {
        this.eiPublisher = eiPublisher;
    }

    public Long getEiTarget() {
        return eiTarget;
    }

    public void setEiTarget(Long eiTarget) {
        this.eiTarget = eiTarget;
    }

    public Date getEiTime() {
        return eiTime;
    }

    public void setEiTime(Date eiTime) {
        this.eiTime = eiTime;
    }

    public Integer getEiScore() {
        return eiScore;
    }

    public void setEiScore(Integer eiScore) {
        this.eiScore = eiScore;
    }

    public String getEiExtra1() {
        return eiExtra1;
    }

    public void setEiExtra1(String eiExtra1) {
        this.eiExtra1 = eiExtra1 == null ? null : eiExtra1.trim();
    }

    public String getEiExtra2() {
        return eiExtra2;
    }

    public void setEiExtra2(String eiExtra2) {
        this.eiExtra2 = eiExtra2 == null ? null : eiExtra2.trim();
    }

    public Integer getEiCiCode() {
        return eiCiCode;
    }

    public void setEiCiCode(Integer eiCiCode) {
        this.eiCiCode = eiCiCode;
    }

    public String getEiContent() {
        return eiContent;
    }

    public void setEiContent(String eiContent) {
        this.eiContent = eiContent == null ? null : eiContent.trim();
    }
}