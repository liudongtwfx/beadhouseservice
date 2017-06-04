package com.liudong.DAO.Location;

import com.liudong.model.Location.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by liudong on 2017/1/13.
 */
public interface CityLocationRepository extends CrudRepository<City, Integer> {
    List<City> findByProvinceId(int provinceID);

    City findByCityId(int cityId);
}
