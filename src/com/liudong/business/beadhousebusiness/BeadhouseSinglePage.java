package com.liudong.business.beadhousebusiness;

import com.liudong.DAO.BeadHouse.BeadhouseImageManageRepository;
import com.liudong.DAO.BeadHouse.BeadhouseInfoRepository;
import com.liudong.model.Beadhouse.BeadhouseImageManage;
import com.liudong.model.Beadhouse.BeadhouseInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by liudong on 17-7-2.
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
