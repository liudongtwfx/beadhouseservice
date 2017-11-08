package main.java.com.beadhouse.DAO.Location;

import main.java.com.beadhouse.model.location.Province;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by beadhouse on 2017/1/13.
 */
public interface ProvinceRepository extends CrudRepository<Province, Integer> {
    Province findById(int id);

    Province findByProvinceId(int provinceId);
}
