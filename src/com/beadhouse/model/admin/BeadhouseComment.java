package com.beadhouse.model.admin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by beadhouse on 17-7-14.
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
    @Column(name = "reply_id")
    private int beadhousereply;
    private int commentor;
    private boolean anonymous;
    private boolean replyed;
    private float score;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("content").append(":").append(content).append("||")
                .append("beadhouseid").append(":").append(beadhouseid).append("||")
                .append("commentor").append(":").append(commentor).append("||")
                .append("anonymous").append(":").append(anonymous).append("||")
                .append("replyed").append(":").append(replyed).append("||")
                .append("score").append(":").append(score);
        return sb.toString();
    }
}
