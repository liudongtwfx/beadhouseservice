package com.liudong.DAO.BeadHouse;

import com.liudong.model.Beadhouse.BeadhouseImageManage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liudong on 2017/5/17.
 */
public interface BeadhouseImageManageRepository extends JpaRepository<BeadhouseImageManage, Long> {
    List<BeadhouseImageManage> findByBeadhouseid(int beadhouseid);
    List<BeadhouseImageManage> findByBeadhouseidOrderByImagePriorityAsc(int beadhouseid);
}
