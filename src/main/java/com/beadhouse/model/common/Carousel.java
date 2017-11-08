package main.java.com.beadhouse.model.common;

import main.java.com.beadhouse.business.CommonFunctions;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by beadhouse on 17-6-19.
 */

@Data
@Entity
@Table(name = "carousels")
public class Carousel {
    @Id
    @GeneratedValue
    short id;
    private String path;
    private String description;
    private String location;

    public static final String imageUrl = "http://" + CommonFunctions.getIpAddress() + ":8088/imagefiles/carousel";
    public static final String realPath;

    static {
        if (System.getProperty("os.name").contains("Window")) {
            realPath = "E:\\Users\\beadhouse\\IdeaProjects\\login\\web\\WEB-INF\\imagefiles\\carousel";
        } else {
            realPath = "/home/beadhouse/ideaProject/beadhouseservice/web/WEB-INF/imagefiles/carousel";
        }
    }
}
