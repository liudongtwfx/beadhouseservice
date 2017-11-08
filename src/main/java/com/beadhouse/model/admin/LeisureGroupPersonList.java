package main.java.com.beadhouse.model.admin;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="leisuregrouppersonlist")
@Data
public class LeisureGroupPersonList {
    @Id
    private int id;
    private int groupId;
    private int elderId;
    private String elderName;
    private Date addtime;
}
