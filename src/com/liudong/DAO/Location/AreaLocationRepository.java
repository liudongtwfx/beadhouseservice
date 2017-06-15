package com.liudong.DAO.Location;

import com.liudong.model.Location.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liudong on 2017/1/13.
 */
public interface AreaLocationRepository extends JpaRepository<Area, Integer> {
    List<Area> findByCityId(int cityId);

    Area findByAreaId(String areaId);
}
