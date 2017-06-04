package com.liudong.controller;

import com.liudong.DAO.User.ElderPeople.ElderPeopleRepository;
import com.liudong.DAO.User.VipUser.VipUserRepository;
import com.liudong.model.User.ElderPeople;
import com.liudong.model.User.VipUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liudong on 2017/3/2.
 */
@Controller
@RequestMapping(value = "/user/usercenter")
public class UserCenterController {
    @Inject
    ElderPeopleRepository elderPeopleRepository;
    @Inject
    VipUserRepository vipUserManager;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userCenter() {
        return "vipuser/usercenter";
    }

    @RequestMapping(value = "getIdNumber", method = RequestMethod.GET)
    @ResponseBody
    public boolean idNumberExist(HttpServletRequest request, HttpServletResponse response) {
        ElderPeople elderPeople = this.elderPeopleRepository.findByIdNumber(request.getParameter("idNumber"));
        return elderPeople != null;
    }

    @RequestMapping(value = "elderRegister", method = RequestMethod.POST)
    @ResponseBody
    public boolean elderRegister(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.getAttribute("userName");
        String username = (String) session.getAttribute("userName");
        ElderPeople elderPeople = new ElderPeople();
        elderPeople.setName(request.getParameter("elderName"));
        elderPeople.setGender(request.getParameter("elderGender"));
        elderPeople.setIdNumber(request.getParameter("elderIdNumber"));
        elderPeople.setLocationId(Integer.parseInt(request.getParameter("elderAreaId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date date;
        try {
            date = sdf.parse(request.getParameter("elderBirthdate"));
            elderPeople.setBirthDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        elderPeople.setVipuserId(this.vipUserManager.findByUserName(username).getId());
        this.elderPeopleRepository.save(elderPeople);
        return true;
    }

    @RequestMapping(value = "elderlist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getElderList(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getSession().getAttribute("userName");
        VipUser user = this.vipUserManager.findByUserName(username);
        if (username == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("list", this.elderPeopleRepository.findByVipuserId(user.getId()));
        return map;
    }
}
