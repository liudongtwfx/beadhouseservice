package com.liudong.model.admin;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by liudong on 17-7-14.
 */
@Entity
@Data
@Table(name = "beadhousecomment")
public class BeadhouseComment {
    @Id
    private int id;
    private String content;
    private Date addtime;
    private int beadhosueid;
    private int beadhousereply;
    private int commentor;
    private boolean anonymous;
    private float score;
}
