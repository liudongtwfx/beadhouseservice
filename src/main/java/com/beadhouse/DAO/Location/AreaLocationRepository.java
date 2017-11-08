package main.java.com.beadhouse.DAO.Location;

import main.java.com.beadhouse.model.location.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by beadhouse on 2017/1/13.
 */
public interface AreaLocationRepository extends JpaRepository<Area, Integer> {
    List<Area> findByCityId(int cityId);

    Area findByAreaId(String areaId);
}
