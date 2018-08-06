package com.rofe.pojo;

import java.util.Date;

public class Student {
    private Long stuUserid;

    private String stuUsername;

    private String stuPassword;

    private String stuPrePassword;

    private Long stuNum;

    private String stuName;

    private Integer stuGrade;

    private Long stuClass;

    private String stuTel;

    private Date stuBirthday;

    private Byte stuSex;

    private Integer stuLock;

    private Byte stuFirstlogin;

    private String stuExtra1;

    private String stuExtra2;

    private String stuExtra3;

    private Integer stuExtra4;

    private Integer stuExtra5;

    private Float stuFinalscore;

    private Integer stuInstorEi;

    private Integer stuArmyEi;

    private Integer stuTutorEi;

    private Integer stuDeptid;

    private String stuEmail;

    private Integer stuRole;
    
    private Department dept;
    
    private ClassInfo classInfo;
    
    private Role role;
    

    public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getStuUserid() {
        return stuUserid;
    }

    public void setStuUserid(Long stuUserid) {
        this.stuUserid = stuUserid;
    }

    public String getStuUsername() {
        return stuUsername;
    }

    public void setStuUsername(String stuUsername) {
        this.stuUsername = stuUsername == null ? null : stuUsername.trim();
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword == null ? null : stuPassword.trim();
    }

    public String getStuPrePassword() {
        return stuPrePassword;
    }

    public void setStuPrePassword(String stuPrePassword) {
        this.stuPrePassword = stuPrePassword == null ? null : stuPrePassword.trim();
    }

    public Long getStuNum() {
        return stuNum;
    }

    public void setStuNum(Long stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public Integer getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(Integer stuGrade) {
        this.stuGrade = stuGrade;
    }

    public Long getStuClass() {
        return stuClass;
    }

    public void setStuClass(Long stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuTel() {
        return stuTel;
    }

    public void setStuTel(String stuTel) {
        this.stuTel = stuTel == null ? null : stuTel.trim();
    }

    public Date getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(Date stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public Byte getStuSex() {
        return stuSex;
    }

    public void setStuSex(Byte stuSex) {
        this.stuSex = stuSex;
    }

    public Integer getStuLock() {
        return stuLock;
    }

    public void setStuLock(Integer stuLock) {
        this.stuLock = stuLock;
    }

    public Byte getStuFirstlogin() {
        return stuFirstlogin;
    }

    public void setStuFirstlogin(Byte stuFirstlogin) {
        this.stuFirstlogin = stuFirstlogin;
    }

    public String getStuExtra1() {
        return stuExtra1;
    }

    public void setStuExtra1(String stuExtra1) {
        this.stuExtra1 = stuExtra1 == null ? null : stuExtra1.trim();
    }

    public String getStuExtra2() {
        return stuExtra2;
    }

    public void setStuExtra2(String stuExtra2) {
        this.stuExtra2 = stuExtra2 == null ? null : stuExtra2.trim();
    }

    public String getStuExtra3() {
        return stuExtra3;
    }

    public void setStuExtra3(String stuExtra3) {
        this.stuExtra3 = stuExtra3 == null ? null : stuExtra3.trim();
    }

    public Integer getStuExtra4() {
        return stuExtra4;
    }

    public void setStuExtra4(Integer stuExtra4) {
        this.stuExtra4 = stuExtra4;
    }

    public Integer getStuExtra5() {
        return stuExtra5;
    }

    public void setStuExtra5(Integer stuExtra5) {
        this.stuExtra5 = stuExtra5;
    }

    public Float getStuFinalscore() {
        return stuFinalscore;
    }

    public void setStuFinalscore(Float stuFinalscore) {
        this.stuFinalscore = stuFinalscore;
    }

    public Integer getStuInstorEi() {
        return stuInstorEi;
    }

    public void setStuInstorEi(Integer stuInstorEi) {
        this.stuInstorEi = stuInstorEi;
    }

    public Integer getStuArmyEi() {
        return stuArmyEi;
    }

    public void setStuArmyEi(Integer stuArmyEi) {
        this.stuArmyEi = stuArmyEi;
    }

    public Integer getStuTutorEi() {
        return stuTutorEi;
    }

    public void setStuTutorEi(Integer stuTutorEi) {
        this.stuTutorEi = stuTutorEi;
    }

    public Integer getStuDeptid() {
        return stuDeptid;
    }

    public void setStuDeptid(Integer stuDeptid) {
        this.stuDeptid = stuDeptid;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail == null ? null : stuEmail.trim();
    }

    public Integer getStuRole() {
        return stuRole;
    }

    public void setStuRole(Integer stuRole) {
        this.stuRole = stuRole;
    }
}