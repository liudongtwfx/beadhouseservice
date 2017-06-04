package com.liudong.DAO.BeadHouse;

import com.liudong.model.Beadhouse.BeadhouseElderCheckin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liudong on 2017/4/13.
 */
public interface BeadhouseElderCheckinRepository extends JpaRepository<BeadhouseElderCheckin, Integer> {
    BeadhouseElderCheckin findByElderIdNumber(String elderId);

    List<BeadhouseElderCheckin> findByBeadhouseId(int beadhouseid);
}
