package main.java.com.beadhouse.DAO.Location;

import main.java.com.beadhouse.model.location.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by beadhouse on 2017/1/13.
 */
public interface CityLocationRepository extends CrudRepository<City, Integer> {
    List<City> findByProvinceId(int provinceID);

    City findByCityId(int cityId);
}
