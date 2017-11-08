package main.java.com.beadhouse.controller.admin;

import main.java.com.beadhouse.DAO.Admin.AdminInfoRepostory;
import main.java.com.beadhouse.DAO.Generiac.DefaultAuthenticationService;
import main.java.com.beadhouse.model.admin.AdminInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by beadhouse on 2017/5/25.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminIndexPage {
    @Inject
    AdminInfoRepostory adminInfoRepostory;
    @Inject
    DefaultAuthenticationService authenticationService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String adminLoginPage() {
        return "admin/login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String adminRegisterPage() {
        return "admin/register";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String adminIndexPage() {
        return "admin/index";
    }

    @RequestMapping(value = "vipuser", method = RequestMethod.GET)
    public String vipUserPage() {
        return "admin/vipuser";
    }

    @RequestMapping(value = "elderpeople", method = RequestMethod.GET)
    public String elderPeoplePage() {
        return "admin/elderpeople";
    }

    @RequestMapping(value = "beadhouse", method = RequestMethod.GET)
    public String beadhouseListPage() {
        return "admin/beadhouselist";
    }

    @RequestMapping(value = "beadhouse/singlepage", method = RequestMethod.GET)
    public String beadhouseSinglePage(HttpServletRequest request) {
        request.getSession().setAttribute("beadhouseId", request.getParameter("beadhouseId"));
        return "admin/beadhousesingle";
    }

    @RequestMapping(value = "homepage", method = RequestMethod.GET)
    public String homePageSetting() {
        return "admin/userhomepage";
    }

    @RequestMapping(value = "register/administratorInfo", method = RequestMethod.GET)
    @ResponseBody
    public boolean ifExist(HttpServletRequest request) {
        String param;
        if ((param = request.getParameter("adminName")) != null) {
            return this.adminInfoRepostory.findByUsername(param) != null;
        }
        if ((param = request.getParameter("telephoneNumber")) != null) {
            return this.adminInfoRepostory.findByTelephoneNumber(param) != null;
        }
        if ((param = request.getParameter("emailAddress")) != null) {
            return this.adminInfoRepostory.findByEmailAddress(param) != null;
        }
        return false;
    }

    @RequestMapping(value = "getAdminUserName", method = RequestMethod.GET)
    @ResponseBody
    public String forKeepAlive(HttpServletRequest request) {
        String name = (String) request.getSession().getAttribute("adminName");
        AdminInfo admin = this.adminInfoRepostory.findByUsername(name);
        return admin != null ? admin.getUsername() : "";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public boolean adminLogin(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        AdminInfo admin = null;
        if (parameterMap.containsKey("userName")) {
            admin = this.adminInfoRepostory.findByUsername(parameterMap.get("userName")[0]);
        } else {
            if (parameterMap.containsKey("telephoneNumber")) {
                admin = this.adminInfoRepostory.findByTelephoneNumber(parameterMap.get("telephoneNumber")[0]);
            } else {
                if (parameterMap.containsKey("emailAddress")) {
                    admin = this.adminInfoRepostory.findByEmailAddress(parameterMap.get("emailAddress")[0]);
                }
            }
        }
        if (admin == null) {
            return false;
        }
        String pwd = request.getParameter("password");
        if (this.authenticationService.authenticateAdmin(admin.getUsername(), pwd) != null) {
            request.getSession().setAttribute("adminName", admin.getUsername());
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public boolean adminRegister(HttpServletRequest request) {
        if (this.adminInfoRepostory.findByUsername(request.getParameter("userName")) == null) {
            AdminInfo admin = new AdminInfo();
            setAdminInfo(request, admin);
            admin.setAddTime(new Date());
            return this.authenticationService.savaAdmin(admin, admin.getPassword());
        }
        return false;
    }

    private void setAdminInfo(HttpServletRequest request, AdminInfo admin) {
        admin.setUsername(request.getParameter("userName"));
        admin.setPassword(request.getParameter("password"));
        admin.setTelephoneNumber(request.getParameter("telephoneNumber"));
        admin.setChineseName(request.getParameter("realName"));
        admin.setEmployeeId(request.getParameter("employeeId"));
        admin.setEmailAddress(request.getParameter("emailAddress"));
        admin.setDepartment(request.getParameter("department"));
    }
}
