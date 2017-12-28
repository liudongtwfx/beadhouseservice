package main.java.com.beadhouse.controller.beadhouse;

import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseAdministratorRepository;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseInfoRepository;
import main.java.com.beadhouse.DAO.Generiac.DefaultAuthenticationService;
import main.java.com.beadhouse.DAO.User.ElderPeople.ElderPeopleRepository;
import main.java.com.beadhouse.DAO.User.VipUser.VipUserRepository;
import main.java.com.beadhouse.business.beadhousebusiness.BeadhouseAdminBusiness;
import main.java.com.beadhouse.cache.addcachebusiness.BeadhouseManageCacheThread;
import main.java.com.beadhouse.model.beadhouse.BeadhouseAdministrator;
import main.java.com.beadhouse.model.beadhouse.BeadhouseInfo;
import main.java.com.beadhouse.model.user.ElderPeople;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by beadhouse on 2017/1/17.
 */
@Controller
@RequestMapping(value = "/beadhouse")
public class BeadhouseAdminController {
    @Inject
    BeadhouseAdministratorRepository administratorRepository;

    @Inject
    DefaultAuthenticationService authenticationService;

    @Inject
    BeadhouseInfoRepository beadHouseInfoRepository;

    @Inject
    ElderPeopleRepository elderPeopleRepository;

    @Inject
    VipUserRepository vipUserRepository;

    @Inject
    BeadhouseAdminBusiness business;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String adminHomepage(HttpServletRequest request) {
        String beadhouseId = (String) request.getSession().getAttribute("beadhouseId");
        if (beadhouseId != null && beadhouseId.length() != 0) {
            Thread beadhouseManageCacheThread = new Thread(
                    new BeadhouseManageCacheThread(Integer.valueOf(beadhouseId)));
            beadhouseManageCacheThread.start();
        }
        return "beadhouse/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String adminLoginView() {
        return "beadhouse/adminlogin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public boolean adminLogin(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        BeadhouseAdministrator administrator = null;
        if (parameterMap.containsKey("userName")) {
            administrator = this.administratorRepository.findByUserName(parameterMap.get("userName")[0]);
        } else {
            if (parameterMap.containsKey("telephoneNumber")) {
                administrator = this.administratorRepository.findByTelephoneNumber(parameterMap.get("telephoneNumber")[0]);
            } else {
                if (parameterMap.containsKey("emailAddress")) {
                    administrator = this.administratorRepository.findByEmailAddress(parameterMap.get("emailAddress")[0]);
                }
            }
        }
        if (administrator == null) {
            return false;
        }
        String pwd = request.getParameter("password");
        if (this.authenticationService.authenticateBeadhouseAdmin(administrator.getUserName(), pwd) != null) {
            request.getSession().setAttribute("adminUserName", administrator.getUserName());
            request.getSession().setAttribute("beadhouseId", administrator.getBeadHouseId());
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/getAdminUserName", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getAdminUserName(HttpServletRequest request, HttpServletResponse response) {
        String adminName = (String) request.getSession().getAttribute("adminUserName");
        if (adminName == null || adminName.equals("")) {
            return new HashMap<>();
        }
        int id = this.administratorRepository.findByUserName(adminName).getBeadHouseId();
        BeadhouseInfo beadhouseInfo = this.beadHouseInfoRepository.findOne(id);
        Map<String, String> res = new HashMap<>();
        res.put("adminUserName", adminName);
        res.put("beadhouseName", beadhouseInfo.getFullName());
        res.put("beadhouseDescription", beadhouseInfo.getDescription());
        res.put("beadhouseLocation", beadhouseInfo.getFullLocation());
        res.put("beadhouseLNG", String.valueOf(beadhouseInfo.getLng()));
        res.put("beadhouseLAT", String.valueOf(beadhouseInfo.getLat()));
        res.put("beadhouseLinksite", beadhouseInfo.getLinksite());
        res.put("beadhouseContact", beadhouseInfo.getContactInfo());
        res.put("beadhouseRooms", String.valueOf(beadhouseInfo.getTotalRooms()));
        res.put("beadhouseTotalBeds", String.valueOf(beadhouseInfo.getTotalBeds()));
        res.put("beadhouseCurrentBeds", String.valueOf(beadhouseInfo.getCurrentBeds()));
        res.put("beadhouseOtherInfo", beadhouseInfo.getOtherInfo());
        return res;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String adminRegisterView() {
        return "/beadhouse/adminregister";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public boolean adminRegister(HttpServletRequest request) {
        if (this.administratorRepository.findByUserName(request.getParameter("userName")) == null) {
            BeadhouseAdministrator admin = new BeadhouseAdministrator();
            admin.setUserName(request.getParameter("userName"));
            admin.setPassword(request.getParameter("password"));
            admin.setTelephoneNumber(request.getParameter("telephoneNumber"));
            admin.setRealName(request.getParameter("realName"));
            if (request.getParameter("beadhouseId") != null) {
                admin.setBeadHouseId(Integer.valueOf(request.getParameter("beadhouseId")));
            }
            admin.setEmailAddress(request.getParameter("emailAddress"));
            this.authenticationService.savaBeadhouseAdmin(admin, admin.getPassword());
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/register/administratorInfo", method = RequestMethod.GET)
    @ResponseBody
    public boolean adminExist(HttpServletRequest request) {
        String param;
        if ((param = request.getParameter("adminName")) != null) {
            return this.administratorRepository.findByUserName(param) != null;
        }
        if ((param = request.getParameter("telephoneNumber")) != null) {
            return this.administratorRepository.findByTelephoneNumber(param) != null;
        }
        if ((param = request.getParameter("emailAddress")) != null) {
            return this.administratorRepository.findByEmailAddress(param) != null;
        }
        return false;
    }

    @RequestMapping(value = "/beadhouse", method = RequestMethod.GET)
    @ResponseBody
    public Map<Integer, String> getBeadHouse(HttpServletRequest request) {
        String areaId = request.getParameter("areaId");
        Map<Integer, String> beadhouseMap = new HashMap<>();
        if (areaId == null) {
            return null;
        }
        List<BeadhouseInfo> beadHouseInfos = this.beadHouseInfoRepository.findByLocationId(areaId);
        for (BeadhouseInfo beadHouseInfo : beadHouseInfos) {
            beadhouseMap.put(beadHouseInfo.getId(), beadHouseInfo.getFullName());
        }
        return beadhouseMap;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public boolean logOut(HttpServletRequest request) {
        request.getSession().removeAttribute("adminUserName");
        request.getSession().removeAttribute("beadhouseInfo");
        return request.getSession().getAttribute("adminUserName") == null;
    }

    @RequestMapping(value = "/updateBeadhouseInfo", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateBeadhouseInfo(HttpServletRequest request) {
        int beadhouseId = (int) request.getSession().getAttribute("beadhouseId");
        BeadhouseInfo beadhouseInfo = this.beadHouseInfoRepository.findOne(beadhouseId);
        beadhouseInfo.setFullName(request.getParameter("beadhouseName"));
        beadhouseInfo.setDescription(request.getParameter("beadhouseInfo"));
        beadhouseInfo.setFullLocation(request.getParameter("fullLocation"));
        beadhouseInfo.setLng(Double.parseDouble(request.getParameter("lng")));
        beadhouseInfo.setLat(Double.parseDouble(request.getParameter("lat")));
        beadhouseInfo.setLinksite(request.getParameter("linksite"));
        beadhouseInfo.setContactInfo(request.getParameter("contact"));
        beadhouseInfo.setTotalRooms(Integer.valueOf(request.getParameter("rooms")));
        beadhouseInfo.setTotalBeds(Integer.parseInt(request.getParameter("totalbeds")));
        beadhouseInfo.setCurrentBeds(Integer.parseInt(request.getParameter("currentbeds")));
        beadhouseInfo.setOtherInfo(request.getParameter("other"));
        this.beadHouseInfoRepository.saveAndFlush(beadhouseInfo);
        return true;
    }

    @RequestMapping(value = "/eldercheckin", method = RequestMethod.GET)
    public String elderCheckin() {
        return "beadhouse/elder_checkin";
    }

    @RequestMapping(value = "/elderInfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getElderInfo(HttpServletRequest request) {
        String id = request.getParameter("elderIdNum");
        ElderPeople elderPeople = this.elderPeopleRepository.findByIdNumber(id);
        if (elderPeople == null) {
            return null;
        }
        Map<String, String> elderInfoMap = new HashMap<>();
        elderInfoMap.put("elderName", elderPeople.getName());
        elderInfoMap.put("elderAddress", this.business.getLocationByArea(elderPeople.getLocationId()));
        elderInfoMap.put("elderContact", elderPeople.getContact());
        elderInfoMap.put("elderFamilyContact", this.vipUserRepository.findOne(elderPeople.getVipuserId()).getTelephoneNumber());
        return elderInfoMap;
    }

    @RequestMapping(value = "/elderhealth", method = RequestMethod.GET)
    public String elderHealth() {
        return "beadhouse/elder_health";
    }

    @RequestMapping(value = "/eldergoout", method = RequestMethod.GET)
    public String elderGoOut() {
        return "beadhouse/elder_goout";
    }

    @RequestMapping(value = "/elderaccident", method = RequestMethod.GET)
    public String elderAccident() {
        return "beadhouse/elder_accident";
    }

    @RequestMapping(value = "/imagemanage", method = RequestMethod.GET)
    public String uploadImage() {
        return "beadhouse/image_manage";
    }
}