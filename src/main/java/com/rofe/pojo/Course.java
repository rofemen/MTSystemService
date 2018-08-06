package com.rofe.pojo;

import java.util.Date;

public class Course {
    private Long ciCode;

    private String ciName;

    private String ciExtra1;

    private String ciExtra2;

    private Date ciStarttime;

    private Integer ciTimelong;

    private Integer ciStatus;

    private Integer ciExam;

    private Long ciTcnum;

    private Integer ciType;

    private String ciDes;
    
    private Teacher ciTc;
    

    public Teacher getCiTc() {
		return ciTc;
	}

	public void setCiTc(Teacher ciTc) {
		this.ciTc = ciTc;
	}

	public Long getCiCode() {
        return ciCode;
    }

    public void setCiCode(Long ciCode) {
        this.ciCode = ciCode;
    }

    public String getCiName() {
        return ciName;
    }

    public void setCiName(String ciName) {
        this.ciName = ciName == null ? null : ciName.trim();
    }

    public String getCiExtra1() {
        return ciExtra1;
    }

    public void setCiExtra1(String ciExtra1) {
        this.ciExtra1 = ciExtra1 == null ? null : ciExtra1.trim();
    }

    public String getCiExtra2() {
        return ciExtra2;
    }

    public void setCiExtra2(String ciExtra2) {
        this.ciExtra2 = ciExtra2 == null ? null : ciExtra2.trim();
    }

    public Date getCiStarttime() {
        return ciStarttime;
    }

    public void setCiStarttime(Date ciStarttime) {
        this.ciStarttime = ciStarttime;
    }

    public Integer getCiTimelong() {
        return ciTimelong;
    }

    public void setCiTimelong(Integer ciTimelong) {
        this.ciTimelong = ciTimelong;
    }

    public Integer getCiStatus() {
        return ciStatus;
    }

    public void setCiStatus(Integer ciStatus) {
        this.ciStatus = ciStatus;
    }

    public Integer getCiExam() {
        return ciExam;
    }

    public void setCiExam(Integer ciExam) {
        this.ciExam = ciExam;
    }

    public Long getCiTcnum() {
        return ciTcnum;
    }

    public void setCiTcnum(Long ciTcnum) {
        this.ciTcnum = ciTcnum;
    }

    public Integer getCiType() {
        return ciType;
    }

    public void setCiType(Integer ciType) {
        this.ciType = ciType;
    }

    public String getCiDes() {
        return ciDes;
    }

    public void setCiDes(String ciDes) {
        this.ciDes = ciDes == null ? null : ciDes.trim();
    }
}