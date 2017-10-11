package com.beadhouse.model.admin;

import com.beadhouse.business.CommonFunctions;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "leisuregroupimagelist")
@Data
public class LeisureGroupImageList {
    @Id
    private int id;
    private int groupId;
    private String imagepath;
    private String imagedescription;
    private Date addtime;
    public static final String imageUrl = "http://" + CommonFunctions.getIpAddress() + ":8088/imagefiles/beadhouseimagefiles";
    public static final String realPath;

    static {
        if (System.getProperty("os.name").contains("Window")) {
            realPath = "E:\\Users\\beadhouse\\IdeaProjects\\login\\web\\WEB-INF\\imagefiles\\leisuregroupimagefiles";
        } else {
            realPath = "/home/beadhouse/ideaProject/beadhouseservice/web/WEB-INF/imagefiles/leisuregroupimagefiles";
        }
    }
}
