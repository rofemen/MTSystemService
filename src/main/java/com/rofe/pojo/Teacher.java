package com.rofe.pojo;

import java.util.Date;

public class Teacher {
    private Long tcUserid;

    private String tcUsername;

    private String tcPassword;

    private String tcPrePassword;

    private Long tcNum;

    private String tcName;

    private Long tcDepartment;

    private String tcTel;

    private Date tcBirthday;

    private Byte tcSex;

    private String tcEmail;

    private int tcRole;

    private Integer tcLock;

    private Byte tcFirstlogin;

    private String tcExtra1;

    private String tcExtra2;

    private String tcExtra3;

    private String tcExtra4;

    private String tcExtra5;

    private Role role;
    
    private Department dept;
    
    public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getTcUserid() {
        return tcUserid;
    }

    public void setTcUserid(Long tcUserid) {
        this.tcUserid = tcUserid;
    }

    public String getTcUsername() {
        return tcUsername;
    }

    public void setTcUsername(String tcUsername) {
        this.tcUsername = tcUsername == null ? null : tcUsername.trim();
    }

    public String getTcPassword() {
        return tcPassword;
    }

    public void setTcPassword(String tcPassword) {
        this.tcPassword = tcPassword == null ? null : tcPassword.trim();
    }

    public String getTcPrePassword() {
        return tcPrePassword;
    }

    public void setTcPrePassword(String tcPrePassword) {
        this.tcPrePassword = tcPrePassword == null ? null : tcPrePassword.trim();
    }

    public Long getTcNum() {
        return tcNum;
    }

    public void setTcNum(Long tcNum) {
        this.tcNum = tcNum;
    }

    public String getTcName() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName == null ? null : tcName.trim();
    }

    public Long getTcDepartment() {
        return tcDepartment;
    }

    public void setTcDepartment(Long tcDepartment) {
        this.tcDepartment = tcDepartment;
    }

    public String getTcTel() {
        return tcTel;
    }

    public void setTcTel(String tcTel) {
        this.tcTel = tcTel == null ? null : tcTel.trim();
    }

    public Date getTcBirthday() {
        return tcBirthday;
    }

    public void setTcBirthday(Date tcBirthday) {
        this.tcBirthday = tcBirthday;
    }

    public Byte getTcSex() {
        return tcSex;
    }

    public void setTcSex(Byte tcSex) {
        this.tcSex = tcSex;
    }

    public String getTcEmail() {
        return tcEmail;
    }

    public void setTcEmail(String tcEmail) {
        this.tcEmail = tcEmail == null ? null : tcEmail.trim();
    }

    public int getTcRole() {
        return tcRole;
    }

    public void setTcRole(int tcRole) {
        this.tcRole = tcRole;
    }

    public Integer getTcLock() {
        return tcLock;
    }

    public void setTcLock(Integer tcLock) {
        this.tcLock = tcLock;
    }

    public Byte getTcFirstlogin() {
        return tcFirstlogin;
    }

    public void setTcFirstlogin(Byte tcFirstlogin) {
        this.tcFirstlogin = tcFirstlogin;
    }

    public String getTcExtra1() {
        return tcExtra1;
    }

    public void setTcExtra1(String tcExtra1) {
        this.tcExtra1 = tcExtra1 == null ? null : tcExtra1.trim();
    }

    public String getTcExtra2() {
        return tcExtra2;
    }

    public void setTcExtra2(String tcExtra2) {
        this.tcExtra2 = tcExtra2 == null ? null : tcExtra2.trim();
    }

    public String getTcExtra3() {
        return tcExtra3;
    }

    public void setTcExtra3(String tcExtra3) {
        this.tcExtra3 = tcExtra3 == null ? null : tcExtra3.trim();
    }

    public String getTcExtra4() {
        return tcExtra4;
    }

    public void setTcExtra4(String tcExtra4) {
        this.tcExtra4 = tcExtra4 == null ? null : tcExtra4.trim();
    }

    public String getTcExtra5() {
        return tcExtra5;
    }

    public void setTcExtra5(String tcExtra5) {
        this.tcExtra5 = tcExtra5 == null ? null : tcExtra5.trim();
    }
}