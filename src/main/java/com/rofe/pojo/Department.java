package com.rofe.pojo;

public class Department {
    private Long diId;

    private String diName;

    private String company;

    public Long getDiId() {
        return diId;
    }

    public void setDiId(Long diId) {
        this.diId = diId;
    }

    public String getDiName() {
        return diName;
    }

    public void setDiName(String diName) {
        this.diName = diName == null ? null : diName.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }
}