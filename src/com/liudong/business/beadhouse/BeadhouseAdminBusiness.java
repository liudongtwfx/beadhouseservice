package com.liudong.business.beadhouse;

import com.liudong.DAO.Location.AreaLocationRepository;
import com.liudong.DAO.Location.CityLocationRepository;
import com.liudong.DAO.Location.ProvinceRepository;
import com.liudong.DAO.User.VipUser.VipUserRepository;
import com.liudong.model.Location.Area;
import com.liudong.model.Location.City;
import com.liudong.model.Location.Province;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by liudong on 2017/3/23.
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
        Area area = this.areasLocationRepository.findByAreaId(areaid);
        City city = this.cityRepository.findByCityId(area.getCityId());
        Province province = this.provinceRepository.findByProvinceId(city.getProvinceId());
        return province.getProvince() + city.getCity() + area.getArea();
    }
}
