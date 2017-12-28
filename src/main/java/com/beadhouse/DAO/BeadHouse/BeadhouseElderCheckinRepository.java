package main.java.com.beadhouse.DAO.BeadHouse;

import main.java.com.beadhouse.model.beadhouse.BeadhouseElderCheckin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by beadhouse on 2017/4/13.
 */
public interface BeadhouseElderCheckinRepository extends JpaRepository<BeadhouseElderCheckin, Integer> {
    List<BeadhouseElderCheckin> findByElderIdNumber(String elderId);

    List<BeadhouseElderCheckin> findByBeadhouseId(int beadhouseid);

    List<BeadhouseElderCheckin> findByElderIdNumberOrderByCheckinTimeDesc(String elderId);

    Page<BeadhouseElderCheckin> findByBeadhouseId(int beadhouseid, Sort sort);
}
