package com.liudong.model.Beadhouse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by liudong on 2017/5/17.
 */
@Entity
@Table(name = "beadhouseimageinfo")
public class BeadhouseImageManage {
    private long id;
    private String imageDescription;
    private String imagePath;
    private int imagePriority;
    private int beadhouseid;

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getImagePriority() {
        return imagePriority;
    }

    public void setImagePriority(int imagePriority) {
        this.imagePriority = imagePriority;
    }

    public int getBeadhouseid() {
        return beadhouseid;
    }

    public void setBeadhouseid(int beadhouseid) {
        this.beadhouseid = beadhouseid;
    }
}
