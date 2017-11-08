package main.java.com.beadhouse.model.beadhouse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by beadhouse on 2017/5/8.
 */

@Entity
@Table(name = "beadhouseelderaccident")
public class BeadhouseElderAccident {
    private long id;
    private String elderIdNumber;
    private String accidentType;
    private String reason;
    private Date happenTime;
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

    public String getAccidentType() {
        return this.accidentType;
    }

    public void setAccidentType(String type) {
        this.accidentType = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(Date happenTime) {
        this.happenTime = happenTime;
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
