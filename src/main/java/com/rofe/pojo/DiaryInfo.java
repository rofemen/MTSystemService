package com.rofe.pojo;

import java.util.Date;

public class DiaryInfo {
    private Integer diaryId;

    private String diaryTitle;

    private Date diaryTime;

    private Integer diaryPublicer;

    private Integer diaryExtra1;

    private String diaryExtra2;

    private String diaryContent;
    
    private Student publicer;
    
    

    public Student getPublicer() {
		return publicer;
	}

	public void setPublicer(Student publicer) {
		this.publicer = publicer;
	}

	public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle == null ? null : diaryTitle.trim();
    }

    public Date getDiaryTime() {
        return diaryTime;
    }

    public void setDiaryTime(Date diaryTime) {
        this.diaryTime = diaryTime;
    }

    public Integer getDiaryPublicer() {
        return diaryPublicer;
    }

    public void setDiaryPublicer(Integer diaryPublicer) {
        this.diaryPublicer = diaryPublicer;
    }

    public Integer getDiaryExtra1() {
        return diaryExtra1;
    }

    public void setDiaryExtra1(Integer diaryExtra1) {
        this.diaryExtra1 = diaryExtra1;
    }

    public String getDiaryExtra2() {
        return diaryExtra2;
    }

    public void setDiaryExtra2(String diaryExtra2) {
        this.diaryExtra2 = diaryExtra2 == null ? null : diaryExtra2.trim();
    }

    public String getDiaryContent() {
        return diaryContent;
    }

    public void setDiaryContent(String diaryContent) {
        this.diaryContent = diaryContent == null ? null : diaryContent.trim();
    }
}