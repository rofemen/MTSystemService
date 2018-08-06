package com.rofe.pojo;

public class CourseScore {
    private Long csId;

    private Long csCiCode;

    private Long csStuUserid;

    private Float csScore;

    private String csExtra1;
    
    private Student csStu;
    

    public Student getCsStu() {
		return csStu;
	}

	public void setCsStu(Student csStu) {
		this.csStu = csStu;
	}

	public Long getCsId() {
        return csId;
    }

    public void setCsId(Long csId) {
        this.csId = csId;
    }

    public Long getCsCiCode() {
        return csCiCode;
    }

    public void setCsCiCode(Long csCiCode) {
        this.csCiCode = csCiCode;
    }

    public Long getCsStuUserid() {
        return csStuUserid;
    }

    public void setCsStuUserid(Long csStuUserid) {
        this.csStuUserid = csStuUserid;
    }

    public Float getCsScore() {
        return csScore;
    }

    public void setCsScore(Float csScore) {
        this.csScore = csScore;
    }

    public String getCsExtra1() {
        return csExtra1;
    }

    public void setCsExtra1(String csExtra1) {
        this.csExtra1 = csExtra1 == null ? null : csExtra1.trim();
    }
}