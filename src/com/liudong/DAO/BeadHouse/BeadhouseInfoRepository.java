package com.liudong.DAO.BeadHouse;

import com.liudong.model.Beadhouse.BeadhouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liudong on 2017/2/27.
 */
public interface BeadhouseInfoRepository extends JpaRepository<BeadhouseInfo, Integer> {
    BeadhouseInfo findByFullName(String fullname);

    List<BeadhouseInfo> findByLocationId(int id);
}
