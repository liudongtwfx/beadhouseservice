package com.liudong.model.Beadhouse;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by liudong on 2017/5/2.
 */

@Entity(name = "beadhouseelderhealth")
public class BeadhouseElderHealth {
    private int id;
    private String elderIdNumber;
    private int lowBloodPressure;
    private int highBloodPressure;
    private int heartRate;
    private Date examingTime;
    private Date updateTime;
    private String healthStatus;
    private String nursingResult;
    private int beadhouseId;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getElderIdNumber() {
        return elderIdNumber;
    }

    public void setElderIdNumber(String elderIdNumber) {
        this.elderIdNumber = elderIdNumber;
    }

    public int getLowBloodPressure() {
        return lowBloodPressure;
    }

    public void setLowBloodPressure(int lowBloodPressure) {
        this.lowBloodPressure = lowBloodPressure;
    }

    public int getHighBloodPressure() {
        return highBloodPressure;
    }

    public void setHighBloodPressure(int highBloodPressure) {
        this.highBloodPressure = highBloodPressure;
    }

    public Date getExamingTime() {
        return examingTime;
    }

    public void setExamingTime(Date examingTime) {
        this.examingTime = examingTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getNursingResult() {
        return nursingResult;
    }

    public void setNursingResult(String nursingResult) {
        this.nursingResult = nursingResult;
    }


    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }


    public int getBeadhouseId() {
        return beadhouseId;
    }

    public void setBeadhouseId(int beadhouseId) {
        this.beadhouseId = beadhouseId;
    }
}
