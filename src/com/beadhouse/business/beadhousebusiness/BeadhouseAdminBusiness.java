package com.beadhouse.business.beadhousebusiness;

import com.beadhouse.DAO.Location.AreaLocationRepository;
import com.beadhouse.DAO.Location.CityLocationRepository;
import com.beadhouse.DAO.Location.ProvinceRepository;
import com.beadhouse.DAO.User.VipUser.VipUserRepository;
import com.beadhouse.model.location.Area;
import com.beadhouse.model.location.City;
import com.beadhouse.model.location.Province;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by beadhouse on 2017/3/23.
 */

@Service
public class BeadhouseAdminBusiness {
    @Inject
    AreaLocationRepository areasLocationRepository;

    @Inject
    CityLocationRepository cityRepository;

    @Inject
    ProvinceRepository provinceRepository;

    @Inject
    VipUserRepository vipUserRepository;

    @Transactional(readOnly = true)
    public String getLocationByArea(int areaid) {
        try {
            Area area = this.areasLocationRepository.findByAreaId(String.valueOf(areaid));
            City city = this.cityRepository.findByCityId(area.getCityId());
            Province province = this.provinceRepository.findByProvinceId(city.getProvinceId());
            return province.getProvince() + city.getCity() + area.getArea();
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

    @Transactional
    public List<Area> getAllAreas() {
        return this.areasLocationRepository.findAll();
    }
}
