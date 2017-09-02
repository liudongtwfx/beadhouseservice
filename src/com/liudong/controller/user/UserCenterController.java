package com.liudong.controller.user;

import com.liudong.DAO.User.ElderPeople.ElderPeopleRepository;
import com.liudong.DAO.User.VipUser.VipUserRepository;
import com.liudong.business.beadhousebusiness.ArticleRecommend;
import com.liudong.business.beadhousebusiness.BeadhouseAdminBusiness;
import com.liudong.business.beadhousebusiness.BeadhouseElderInfoBusiness;
import com.liudong.business.beadhousebusiness.BeadhouseRecommend;
import com.liudong.model.Location.Area;
import com.liudong.model.User.ElderPeople;
import com.liudong.model.User.VipUser;
import com.liudong.test.IdCardGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Inject
    BeadhouseAdminBusiness business;

    @Inject
    BeadhouseElderInfoBusiness beadhouseElderInfoBusiness;

    @Inject
    BeadhouseRecommend beadhouseRecommend;

    @Inject
    ArticleRecommend articleRecommend;

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
        Map<String, Object> resMap = new HashMap<>();
        List<ElderPeople> elderList = this.elderPeopleRepository.findByVipuserId(user.getId());
        if (elderList == null) {
            return resMap;
        }
        resMap.put("list", elderList);
        resMap.put("elderInfo", this.beadhouseElderInfoBusiness.getElderInfo(elderList.get(0)));
        return resMap;
    }

    @RequestMapping(value = "checkinId", method = RequestMethod.GET)
    @ResponseBody
    public Object getOtherInfosBasedOnCheckin(HttpServletRequest request) {
        String checkinId = request.getParameter("id");
        if (checkinId == null || checkinId.length() == 0) {
            return null;
        }
        return this.beadhouseElderInfoBusiness.getInfosBasedOnCheckinId(Integer.valueOf(checkinId));
    }

    @RequestMapping(value = "getRecommendBeadhouse", method = RequestMethod.GET)
    @ResponseBody
    public Object getRecommendBeadhouse(HttpServletRequest request) {
        return this.beadhouseRecommend.getBeadhouseRecommend(request);
    }

    @RequestMapping(value = "getRecommendArticle", method = RequestMethod.GET)
    @ResponseBody
    public Object getRecommendArticle(HttpServletRequest request) {
        return this.articleRecommend.getArticleRecommend(request);
    }

//    @RequestMapping(value = "elderRegisterTest", method = RequestMethod.GET)
//    @ResponseBody
//    public String elderRegisterRandom(HttpServletRequest request, HttpServletResponse response) {
//        List<VipUser> vipUsers = this.vipUserManager.findAll();
//        List<Area> areas = this.business.getAllAreas();
//        String[] gender = {"男", "女"};
//        NumberFormat format = NumberFormat.getInstance();
//        format.setMinimumIntegerDigits(2);
//        int added = 0;
//        for (VipUser user : vipUsers) {
//            for (int i = 0; i < 2; i++) {
//                ElderPeople elderPeople = new ElderPeople();
//                elderPeople.setName(IdCardGenerator.getRandomChineseName());
//                elderPeople.setVipuserId(user.getId());
//                while (true) {
//                    try {
//                        Area a = areas.get((int) (Math.random() * areas.size()));
//                        elderPeople.setGender(gender[(int) (Math.random() * 2)]);
//                        String year = String.valueOf((int) (1930 + Math.random() * 35));
//                        String month = format.format((int) Math.random() * 11 + 1);
//                        String day = format.format((int) Math.random() * 29 + 1);
//                        elderPeople.setIdNumber(IdCardGenerator.generate(a.getArea(), year + month + day));
//                        elderPeople.setLocationId(a.getAreaId());
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//                        Date date;
//                        date = sdf.parse(year + "-" + month + "-" + day);
//                        elderPeople.setBirthDate(date);
//                        break;
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//                elderPeople.setVipuserId(user.getId());
//                this.elderPeopleRepository.save(elderPeople);
//                added++;
//            }
//        }
//        return "added:" + added;
//    }
}
