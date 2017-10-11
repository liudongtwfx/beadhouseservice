package com.beadhouse.model.Beadhouse;

import com.beadhouse.business.CommonFunctions;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by beadhouse on 2017/5/17.
 */
@Entity
@Table(name = "beadhouseimageinfo")
public class BeadhouseImageManage {
    public static final String imageUrl = "http://" + CommonFunctions.getIpAddress() + ":8088/imagefiles/beadhouseimagefiles";
    public static final String realPath;

    static {
        if (System.getProperty("os.name").contains("Window")) {
            realPath = "E:\\Users\\beadhouse\\IdeaProjects\\login\\web\\WEB-INF\\imagefiles\\beadhouseimagefiles";
        } else {
            realPath = "/home/beadhouse/ideaProject/beadhouseservice/web/WEB-INF/imagefiles/beadhouseimagefiles";
        }
    }

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
