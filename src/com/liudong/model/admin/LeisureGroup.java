package com.liudong.model.admin;

import com.liudong.System.LeisureGroupStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "leisuregroup")
@Data
public class LeisureGroup {
    @Id
    private int id;
    private String title;
    private String details;
    private Date addtime;
    private LeisureGroupStatus groupstatus;
    private int numberofpeople;
    private Date updatetime;
}