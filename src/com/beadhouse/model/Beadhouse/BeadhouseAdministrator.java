package com.beadhouse.model.Beadhouse;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by beadhouse on 2017/1/17.
 */
@Entity
@Table(name = "beadhouseadmininfo")
public class BeadhouseAdministrator {
    private int id;
    private String userName;
    private String realName;
    private String password;
    private int beadHouseId;
    private String emailAddress;
    private String telephoneNumber;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAdress) {
        this.emailAddress = emailAdress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getBeadHouseId() {
        return beadHouseId;
    }

    public void setBeadHouseId(int beadHouseId) {
        this.beadHouseId = beadHouseId;
    }
}
