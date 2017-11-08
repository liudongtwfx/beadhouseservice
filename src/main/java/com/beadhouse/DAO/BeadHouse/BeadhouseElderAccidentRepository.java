package main.java.com.beadhouse.DAO.BeadHouse;

import main.java.com.beadhouse.model.beadhouse.BeadhouseElderAccident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by beadhouse on 2017/5/8.
 */
public interface BeadhouseElderAccidentRepository extends JpaRepository<BeadhouseElderAccident, Long> {
    List<BeadhouseElderAccident> findByElderIdNumber(String elderId);

    List<BeadhouseElderAccident> findByBeadhouseId(int beadhouseid);
}
