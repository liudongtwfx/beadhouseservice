package com.liudong.model.admin;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="leisuregroupimagelist")
@Data
public class LeisureGroupImageList {
    private int id;
    private int groupId;
    private String imagepath;
    private String imagedescription;
    private Date addtime;
}
