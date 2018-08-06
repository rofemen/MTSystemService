package com.rofe.pojo;

public class ClassInfo {
    private Long clCode;

    private Long clDpid;

    private Integer clGrade;

    private String clName;

    private Integer clCount;

    private Integer clMaxcount;

    private String clRowname;

    private Long clInstor;

    private Long clArmyInstor;

    private Long clTutor;

    private String clExtra1;

    private String clExtra2;

    private String clExtra3;
    
    private Teacher instor;
    
    private Teacher armyInstor;
    
    private Teacher tutor;
    
    private Department dept;
    
    public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Teacher getInstor() {
		return instor;
	}

	public void setInstor(Teacher instor) {
		this.instor = instor;
	}

	public Teacher getArmyInstor() {
		return armyInstor;
	}

	public void setArmyInstor(Teacher armyInstor) {
		this.armyInstor = armyInstor;
	}

	public Teacher getTutor() {
		return tutor;
	}

	public void setTutor(Teacher tutor) {
		this.tutor = tutor;
	}

    public Long getClCode() {
        return clCode;
    }

    public void setClCode(Long clCode) {
        this.clCode = clCode;
    }

    public Long getClDpid() {
        return clDpid;
    }

    public void setClDpid(Long clDpid) {
        this.clDpid = clDpid;
    }

    public Integer getClGrade() {
        return clGrade;
    }

    public void setClGrade(Integer clGrade) {
        this.clGrade = clGrade;
    }

    public String getClName() {
        return clName;
    }

    public void setClName(String clName) {
        this.clName = clName == null ? null : clName.trim();
    }

    public Integer getClCount() {
        return clCount;
    }

    public void setClCount(Integer clCount) {
        this.clCount = clCount;
    }

    public Integer getClMaxcount() {
        return clMaxcount;
    }

    public void setClMaxcount(Integer clMaxcount) {
        this.clMaxcount = clMaxcount;
    }

    public String getClRowname() {
        return clRowname;
    }

    public void setClRowname(String clRowname) {
        this.clRowname = clRowname == null ? null : clRowname.trim();
    }

    public Long getClInstor() {
        return clInstor;
    }

    public void setClInstor(Long clInstor) {
        this.clInstor = clInstor;
    }

    public Long getClArmyInstor() {
        return clArmyInstor;
    }

    public void setClArmyInstor(Long clArmyInstor) {
        this.clArmyInstor = clArmyInstor;
    }

    public Long getClTutor() {
        return clTutor;
    }

    public void setClTutor(Long clTutor) {
        this.clTutor = clTutor;
    }

    public String getClExtra1() {
        return clExtra1;
    }

    public void setClExtra1(String clExtra1) {
        this.clExtra1 = clExtra1 == null ? null : clExtra1.trim();
    }

    public String getClExtra2() {
        return clExtra2;
    }

    public void setClExtra2(String clExtra2) {
        this.clExtra2 = clExtra2 == null ? null : clExtra2.trim();
    }

    public String getClExtra3() {
        return clExtra3;
    }

    public void setClExtra3(String clExtra3) {
        this.clExtra3 = clExtra3 == null ? null : clExtra3.trim();
    }
}