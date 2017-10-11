package com.beadhouse.DAO.Location;

import com.beadhouse.model.Location.Province;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by beadhouse on 2017/1/13.
 */
public interface ProvinceRepository extends CrudRepository<Province, Integer> {
    Province findById(int id);

    Province findByProvinceId(int provinceId);
}
