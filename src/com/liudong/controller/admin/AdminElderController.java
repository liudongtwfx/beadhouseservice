package com.liudong.controller.admin;

import com.liudong.DAO.User.ElderPeople.ElderPeopleRepository;
import com.liudong.business.BeadhouseAdminBusiness;
import com.liudong.model.User.ElderPeople;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liudong on 2017/5/30.
 */

@Controller
@RequestMapping(value = "/admin/elderpeople")
public class AdminElderController {
    class ElderInfo {
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public void setIdNumber(String idNumber) {
            this.idNumber = idNumber;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public Date getAddTime() {
            return addTime;
        }

        public void setAddTime(Date addTime) {
            this.addTime = addTime;
        }

        int id;
        String name;
        String gender;
        String idNumber;
        String location;
        Date birthDate;
        String contact;
        Date addTime;
    }

    @Inject
    ElderPeopleRepository elderPeopleRepository;

    @Inject
    BeadhouseAdminBusiness business;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Page<ElderInfo> getVipUserList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "15") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<ElderPeople> elderPeople = this.elderPeopleRepository.findAll(pageable);
        List<ElderInfo> list = new ArrayList<>();
        for (ElderPeople p : elderPeople.getContent()) {
            ElderInfo info = new ElderInfo();
            info.setId(p.getId());
            info.setName(p.getName());
            info.setGender(p.getGender());
            info.setLocation(this.business.getLocationByArea(p.getLocationId()));
            info.setContact(p.getContact());
            info.setBirthDate(p.getBirthDate());
            info.setAddTime(p.getAddTime());
            info.setIdNumber(p.getIdNumber());
            list.add(info);
        }
        Page<ElderInfo> elderInfos = new PageImpl<ElderInfo>(list);
        return elderInfos;
    }
}
