package com.liudong.model.Location;

import javax.persistence.*;

/**
 * Created by liudong on 2017/1/13.
 */
@Entity
@Table(name = "areas")
public class Area {
    private int id;
    private String areaId;
    private String area;
    private int cityId;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "areaid")
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "cityid")
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
