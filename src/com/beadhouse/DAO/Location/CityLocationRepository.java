package com.beadhouse.DAO.Location;

import com.beadhouse.model.Location.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by beadhouse on 2017/1/13.
 */
public interface CityLocationRepository extends CrudRepository<City, Integer> {
    List<City> findByProvinceId(int provinceID);

    City findByCityId(int cityId);
}
