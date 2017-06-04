package com.liudong.model.Beadhouse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by liudong on 2017/5/6.
 */

@Entity
@Table(name = "beadhouseeldergoout")
public class BeadhouseElderGoOut {
    private long id;
    private String elderIdNumber;
    private Date goOutTime;
    private Date backTime;
    private String reason;
    private Date updateTime;
    private int beadhouseId;

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getElderIdNumber() {
        return elderIdNumber;
    }

    public void setElderIdNumber(String elderIdNumber) {
        this.elderIdNumber = elderIdNumber;
    }

    public Date getGoOutTime() {
        return goOutTime;
    }

    public void setGoOutTime(Date goOutTime) {
        this.goOutTime = goOutTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getBeadhouseId() {
        return beadhouseId;
    }

    public void setBeadhouseId(int beadhouseId) {
        this.beadhouseId = beadhouseId;
    }
}
