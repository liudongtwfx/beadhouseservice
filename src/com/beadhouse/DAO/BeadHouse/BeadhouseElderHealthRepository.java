package com.beadhouse.DAO.BeadHouse;

import com.beadhouse.model.Beadhouse.BeadhouseElderCheckin;
import com.beadhouse.model.Beadhouse.BeadhouseElderHealth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by beadhouse on 2017/5/3.
 */
public interface BeadhouseElderHealthRepository extends JpaRepository<BeadhouseElderHealth, Integer> {
    List<BeadhouseElderCheckin> findByElderIdNumber(String elderId);

    List<BeadhouseElderHealth> findByBeadhouseId(int beadhouseid);

    List<BeadhouseElderHealth> findByElderIdNumberAndExamingTimeBetween(String elderId, Date start, Date end);
}
