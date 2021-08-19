package com.example.covistat;

public class VaccineModel {
    private String centerName,centerAddress,centerFromTime,centerToTime,feeType,vaccineName;
    private int ageLimit,availableCapacity;

    public VaccineModel(String centerName, String centerAddress, String centerFromTime, String centerToTime, String feeType,int ageLimit, String vaccineName, int availableCapacity) {
        this.centerName = centerName;
        this.centerAddress = centerAddress;
        this.centerFromTime = centerFromTime;
        this.centerToTime = centerToTime;
        this.feeType = feeType;
        this.vaccineName = vaccineName;
        this.ageLimit = ageLimit;
        this.availableCapacity = availableCapacity;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public void setCenterFromTime(String centerFromTime) {
        this.centerFromTime = centerFromTime;
    }

    public void setCenterToTime(String centerToTime) {
        this.centerToTime = centerToTime;
    }

    public String getCenterName() {
        return centerName;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public String getCenterFromTime() {
        return centerFromTime;
    }

    public String getCenterToTime() {
        return centerToTime;
    }

    public String getFeeType() {
        return feeType;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }
}
