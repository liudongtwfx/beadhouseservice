package main.java.com.beadhouse.DAO.BeadHouse;

import main.java.com.beadhouse.model.beadhouse.BeadhouseImageManage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by beadhouse on 2017/5/17.
 */
public interface BeadhouseImageManageRepository extends JpaRepository<BeadhouseImageManage, Long> {
    List<BeadhouseImageManage> findByBeadhouseid(int beadhouseid);
    List<BeadhouseImageManage> findByBeadhouseidOrderByImagePriorityAsc(int beadhouseid);
}
