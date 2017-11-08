package main.java.com.beadhouse.model.beadhouse;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by beadhouse on 2017/2/27.
 */
@Entity
@Table(name = "beadhouseinfo")
@Data
public class BeadhouseInfo implements Serializable {
    @Id
    private int id;
    private String fullName;
    private String locationId;
    @Column(name = "longitude")
    private double lng;
    @Column(name = "latitude")
    private double lat;
    private String fullLocation;
    private String description;
    private float score;
    @Column(name = "invitecode")
    private String inviteCode;
    private String linksite;
    @Column(name = "contactinfo")
    private String contactInfo;
    @Column(name = "otherinfo")
    private String otherInfo;
    @Column(name = "totalrooms")
    private Integer totalRooms;
    @Column(name = "totalbeds")
    private Integer totalBeds;
    @Column(name = "currentbeds")
    private Integer currentBeds;
}
