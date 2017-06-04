package com.liudong.DAO.BeadHouse;

import com.liudong.model.Beadhouse.BeadhouseElderAccident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liudong on 2017/5/8.
 */
public interface BeadhouseElderAccidentRepository extends JpaRepository<BeadhouseElderAccident, Long> {
    List<BeadhouseElderAccident> findByElderIdNumber(int elderId);

    List<BeadhouseElderAccident> findByBeadhouseId(int beadhouseid);
}
