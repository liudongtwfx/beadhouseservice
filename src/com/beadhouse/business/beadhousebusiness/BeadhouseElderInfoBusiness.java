package com.beadhouse.business.beadhousebusiness;

import com.beadhouse.DAO.BeadHouse.*;
import com.beadhouse.DAO.User.ElderPeople.ElderPeopleRepository;
import com.beadhouse.model.beadhouse.BeadhouseElderCheckin;
import com.beadhouse.model.user.ElderPeople;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by beadhouse on 17-7-4.
 */

@Service
public class BeadhouseElderInfoBusiness {
    @Inject
    ElderPeopleRepository elderPeopleRepository;

    @Inject
    BeadhouseElderCheckinRepository checkinRepository;

    @Inject
    BeadhouseElderAccidentRepository accidentRepository;

    @Inject
    BeadhouseElderHealthRepository healthRepository;

    @Inject
    BeadhouseElderGoOutRepository goOutRepository;

    @Inject
    BeadhouseInfoRepository beadhouseInfoRepository;

    @Transactional(readOnly = true)
    public Object getElderCheckin(ElderPeople elderPeople) {
        try {
            return this.checkinRepository.findByElderIdNumberOrderByCheckinTimeDesc(elderPeople.getIdNumber());
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Object getElderGoout(ElderPeople elderPeople) {
        try {
            return this.goOutRepository.findByElderIdNumber(elderPeople.getIdNumber());
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Object getElderHealth(ElderPeople elderPeople) {
        try {
            return this.healthRepository.findByElderIdNumber(elderPeople.getIdNumber());
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Object getElderAccident(ElderPeople elderPeople) {
        try {
            return this.accidentRepository.findByElderIdNumber(elderPeople.getIdNumber());
        } catch (Exception e) {
            return null;
        }
    }

    public Object getElderInfo(ElderPeople elderPeople) {
        Map<String, Object> res = new HashMap<>();
        res.put("elderCheckin", getElderCheckin(elderPeople));
        res.put("elderGoout", getElderGoout(elderPeople));
        res.put("elderHealth", getElderHealth(elderPeople));
        res.put("elderAccident", getElderAccident(elderPeople));
        return res;
    }

    @Transactional(readOnly = true)
    public Object getInfosBasedOnCheckinId(int checkinId) {
        BeadhouseElderCheckin checkin = null;
        try {
            checkin = this.checkinRepository.findOne(checkinId);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        if (checkin == null) {
            return null;
        }
        String elderIdNumber = checkin.getElderIdNumber();
        Date start = checkin.getCheckinTime();
        Date leave = checkin.getLeaveTime();
        if (leave == null) {
            leave = new Date();
        }
        Map<String, Object> res = new HashMap<>();
        try {
            res.put("elderHealth", this.healthRepository.findByElderIdNumberAndExamingTimeBetween(elderIdNumber, start, leave));
        } catch (Exception e) {
            System.out.println(e);
            res.put("elerHealth", null);
        }
        res.put("beadhouseName", this.beadhouseInfoRepository.findOne(checkin.getBeadhouseId()).getFullName());
        return res;
    }
}
