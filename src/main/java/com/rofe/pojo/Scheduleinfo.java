package com.rofe.pojo;

import java.util.Date;
import java.util.LinkedHashMap;

import com.rofe.util.CommonTools;

public class Scheduleinfo {
    private Long siId;

    private Long siClCode;

    private Long siCiCode;

    private Long siTcNum;

    private Date siTime;

    private String siExtra1;

    private String siExtra2;

    private Integer siIndex;

    private Teacher siTc;
    
    private ClassInfo siCl;
    
    private Course siCourse;
    
    public Course getSiCourse() {
		return siCourse;
	}

	public void setSiCourse(Course siCourse) {
		this.siCourse = siCourse;
	}

	public Scheduleinfo(){}
    
    public Scheduleinfo(int siIndex){
    	this.siIndex = siIndex;
    }
    public Teacher getSiTc() {
		return siTc;
	}

	public void setSiTc(Teacher siTc) {
		this.siTc = siTc;
	}

	public ClassInfo getSiCl() {
		return siCl;
	}

	public void setSiCl(ClassInfo siCl) {
		this.siCl = siCl;
	}

	public Long getSiId() {
        return siId;
    }

    public void setSiId(Long siId) {
        this.siId = siId;
    }

    public Long getSiClCode() {
        return siClCode;
    }

    public void setSiClCode(Long siClCode) {
        this.siClCode = siClCode;
    }

    public Long getSiCiCode() {
        return siCiCode;
    }

    public void setSiCiCode(Long siCiCode) {
        this.siCiCode = siCiCode;
    }

    public Long getSiTcNum() {
        return siTcNum;
    }

    public void setSiTcNum(Long siTcNum) {
        this.siTcNum = siTcNum;
    }

    public Date getSiTime() {
        return siTime;
    }

    public void setSiTime(Date siTime) {
        this.siTime = siTime;
    }

    public String getSiExtra1() {
        return siExtra1;
    }

    public void setSiExtra1(String siExtra1) {
        this.siExtra1 = siExtra1 == null ? null : siExtra1.trim();
    }

    public String getSiExtra2() {
        return siExtra2;
    }

    public void setSiExtra2(String siExtra2) {
        this.siExtra2 = siExtra2 == null ? null : siExtra2.trim();
    }

    public Integer getSiIndex() {
        return siIndex;
    }

    public void setSiIndex(Integer siIndex) {
        this.siIndex = siIndex;
    }
    
    public static Scheduleinfo getObject(LinkedHashMap<String, Object> map) {
    	Long siId = CommonTools.toLong((Integer)map.get("siId"));
    	if(siId == -1){
    		siId = 0l;
    	}
    	Long siClCode = CommonTools.toLong((Integer)map.get("siClCode"));
    	Long siCiCode = CommonTools.toLong((Integer)map.get("siCiCode"));
    	Long siTcNum = CommonTools.toLong((Integer)map.get("siTcNum"));
    	Date siTime = (Date)map.get("siTime");
		int siIndex = (Integer)map.get("siIndex");;
		return new Scheduleinfo(siId,siClCode,siCiCode,siTcNum,siTime,siIndex);
	}

	public Scheduleinfo(Long siId, Long siClCode, Long siCiCode, Long siTcNum,
			Date siTime,Integer siIndex) {
		super();
		this.siId = siId;
		this.siClCode = siClCode;
		this.siCiCode = siCiCode;
		this.siTcNum = siTcNum;
		this.siTime = siTime;
		this.siIndex = siIndex;
	}
}