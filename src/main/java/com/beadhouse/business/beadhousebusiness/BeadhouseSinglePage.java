package main.java.com.beadhouse.business.beadhousebusiness;

import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseImageManageRepository;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by beadhouse on 17-7-2.
 */

@Service
public class BeadhouseSinglePage {

    @Inject
    BeadhouseInfoRepository beadhouseInfo;

    @Inject
    BeadhouseImageManageRepository beadhouseImageManage;

    @Transactional(readOnly = true)
    public Object getBaseInfo(int id) {
        return this.beadhouseInfo.findOne(id);
    }

    @Transactional(readOnly = true)
    public Object getImageInfo(int id) {
        return this.beadhouseImageManage.findByBeadhouseid(id);
    }
}
