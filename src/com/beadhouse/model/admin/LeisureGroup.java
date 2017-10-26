package com.beadhouse.model.admin;

import lombok.Data;

import javax.persistence.Column;
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
    @Column(name = "maxpeoplecount")
    private int maxPeopleCount;
    private String title;
    private String details;
    private Date addtime;
    @Column(name = "groupstatus")
    private String groupStatus;
    @Column(name = "numberofpeople")
    private int numberOfPeople;
    private Date updatetime;
}