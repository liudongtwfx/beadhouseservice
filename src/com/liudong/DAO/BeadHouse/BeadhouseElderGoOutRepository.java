package com.liudong.DAO.BeadHouse;

import com.liudong.model.Beadhouse.BeadhouseElderGoOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liudong on 2017/5/6.
 */
public interface BeadhouseElderGoOutRepository extends JpaRepository<BeadhouseElderGoOut, Long> {
    List<BeadhouseElderGoOut> findByElderIdNumber(int elderId);

    List<BeadhouseElderGoOut> findByBeadhouseId(int beadhouseId);
}
