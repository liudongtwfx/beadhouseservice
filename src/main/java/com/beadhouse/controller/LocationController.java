package main.java.com.beadhouse.controller;

import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseInfoRepository;
import main.java.com.beadhouse.DAO.Location.AreaLocationRepository;
import main.java.com.beadhouse.DAO.Location.CityLocationRepository;
import main.java.com.beadhouse.DAO.Location.ProvinceRepository;
import main.java.com.beadhouse.model.beadhouse.BeadhouseInfo;
import main.java.com.beadhouse.model.location.Area;
import main.java.com.beadhouse.model.location.City;
import main.java.com.beadhouse.model.location.Province;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Created by beadhouse on 2017/1/12.
 */

@Controller
public class LocationController {
    @Inject
    ProvinceRepository provinceRepository;
    @Inject
    CityLocationRepository citiesLocationRepository;
    @Inject
    AreaLocationRepository areasLocationRepository;

    /**
     * The following filed beadHouseInfoRepository and function getProvinceAndArea() are just used for adding BeadHouseInfo instances;
     */
    @Inject
    BeadhouseInfoRepository beadHouseInfoRepository;

    @RequestMapping(value = "/area", method = RequestMethod.GET)
    public void getProvinceAndArea() {
        String[] beadhouse = {"祥", "康", "福", "泰", "恒", "兴", "庆", "和", "富", "德", "隆"};
        String[] beadhousesuffix = {"养老中心", "养老院"};
        for (int i = 0; i < 100; i++) {
            int areaId = (int) (Math.random() * 3144);
            Area area = this.areasLocationRepository.findOne(areaId);
            City city = this.citiesLocationRepository.findByCityId(area.getCityId());
            Province province = this.provinceRepository.findByProvinceId(city.getProvinceId());
            int beadhouseFirst = (int) (Math.random() * beadhouse.length);
            int beadhousesecond = (int) (Math.random() * beadhouse.length);
            while (beadhousesecond == beadhouseFirst) {
                beadhousesecond = (int) (Math.random() * beadhouse.length);
            }
            int suffix = (int) (Math.random() * 2);
            String beadhouseName = province.getProvince() + city.getCity() + area.getArea() + beadhouse[beadhouseFirst] + beadhouse[beadhousesecond] + beadhousesuffix[suffix];
            BeadhouseInfo beadHouseInfo = new BeadhouseInfo();
            beadHouseInfo.setFullName(beadhouseName);
            beadHouseInfo.setLocationId(String.valueOf(area.getAreaId()));
            this.beadHouseInfoRepository.save(beadHouseInfo);
            //System.out.println(beadhouseName);
        }
    }
}
