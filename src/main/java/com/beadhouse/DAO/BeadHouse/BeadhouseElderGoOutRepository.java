package main.java.com.beadhouse.DAO.BeadHouse;

import main.java.com.beadhouse.model.beadhouse.BeadhouseElderGoOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by beadhouse on 2017/5/6.
 */
public interface BeadhouseElderGoOutRepository extends JpaRepository<BeadhouseElderGoOut, Long> {
    List<BeadhouseElderGoOut> findByElderIdNumber(String elderId);

    List<BeadhouseElderGoOut> findByBeadhouseId(int beadhouseId);
}
