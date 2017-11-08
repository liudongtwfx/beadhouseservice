package main.java.com.beadhouse.model.beadhouse;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by beadhouse on 2017/4/7.
 */
@Entity(name = "beadhouseeldercheckin")
public class BeadhouseElderCheckin {
    private int id;
    private String elderIdNumber;
    private Date checkinTime;
    private Date leaveTime;
    private String leaveReason;
    private String extraContent;
    private int beadhouseId;
    private String principleMan;

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

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getExtraContent() {
        return extraContent;
    }

    public void setExtraContent(String extra) {
        this.extraContent = extra;
    }

    public int getBeadhouseId() {
        return beadhouseId;
    }

    public void setBeadhouseId(int beadhouseId) {
        this.beadhouseId = beadhouseId;
    }

    public String getPrincipleMan() {
        return principleMan;
    }

    public void setPrincipleMan(String principleMan) {
        this.principleMan = principleMan;
    }
}
