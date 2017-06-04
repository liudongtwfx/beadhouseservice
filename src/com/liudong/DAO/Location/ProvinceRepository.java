package com.liudong.DAO.Location;

import com.liudong.model.Location.Province;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by liudong on 2017/1/13.
 */
public interface ProvinceRepository extends CrudRepository<Province, Integer> {
    Province findById(int id);

    Province findByProvinceId(int provinceId);
}
