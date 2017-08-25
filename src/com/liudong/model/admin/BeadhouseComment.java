package com.liudong.model.admin;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by liudong on 17-7-14.
 */
@Entity
@Data
@Table(name = "beadhousecomment")
public class BeadhouseComment implements Serializable {
    @Id
    private int id;
    private String content;
    private Date addtime;
    private int beadhouseid;
    private int beadhousereply;
    private int commentor;
    private boolean anonymous;
    private float score;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append(":").append(id).append("||")
                .append("content").append(":").append(content).append("||")
                .append("beadhouseid").append(":").append(beadhouseid).append("||")
                .append("commentor").append(":").append(commentor).append("||")
                .append("anonymous").append(":").append(anonymous);
        return sb.toString();
    }
}
