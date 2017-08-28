package com.liudong.controller.user;

import com.liudong.DAO.Generiac.DefaultAuthenticationService;
import com.liudong.DAO.User.VipUser.VipUserRepository;
import com.liudong.business.filehandle.FileHandler;
import com.liudong.model.User.VipUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by liudong on 2016/12/21.
 */
@Controller
public class VipUserController {
    @Inject
    VipUserRepository vipUserManager;
    @Inject
    DefaultAuthenticationService authenticationService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<VipUser> user(Model model) throws IOException {
        List<VipUser> list = this.vipUserManager.findAll();
        return list;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public boolean userExist(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.containsKey("name")) {
            return this.vipUserManager.findByUserName(parameterMap.get("name")[0]) != null;
        }
        if (parameterMap.containsKey("telephoneNumber")) {
            return this.vipUserManager.findByTelephoneNumber(parameterMap.get("telephoneNumber")[0]) != null;
        }
        if (parameterMap.containsKey("emailAddress")) {
            return this.vipUserManager.findByEmailAddress(parameterMap.get("emailAddress")[0]) != null;
        }
        return false;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public String vipUserRegister(HttpServletRequest request, HttpServletResponse response) {
        VipUser user = new VipUser();
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        user.setTelephoneNumber(request.getParameter("telephoneNumber"));
        user.setEmailAddress(request.getParameter("emailAddress"));
        try {
            this.authenticationService.saveUser(user, user.getPassword());
            request.getSession().setAttribute("userName", user.getUserName());
            return "success";
        } catch (Exception e) {
            System.out.println(e);
            return "fail";
        }
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public String registerjsp() {
        return "vipuser/register";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String login() {
        return "vipuser/login";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public boolean authenticLogin(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        VipUser user = null;
        if (parameterMap.containsKey("userName")) {
            user = this.vipUserManager.findByUserName(parameterMap.get("userName")[0]);
        } else {
            if (parameterMap.containsKey("telephoneNumber")) {
                user = this.vipUserManager.findByTelephoneNumber(parameterMap.get("telephoneNumber")[0]);
            } else {
                if (parameterMap.containsKey("emailAddress")) {
                    user = this.vipUserManager.findByEmailAddress(parameterMap.get("emailAddress")[0]);
                }
            }
        }
        if (user == null) {
            return false;
        }
        String pwd = request.getParameter("password");
        if (this.authenticationService.authenticateVipUser(user.getUserName(), pwd) != null) {
            request.getSession().setAttribute("userId", String.valueOf(user.getId()));
            request.getSession().setAttribute("userName", user.getUserName());
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/getUserName", method = RequestMethod.GET)
    @ResponseBody
    public String getUserName(HttpServletRequest request, HttpServletResponse response) {
        String name = (String) request.getSession().getAttribute("userName");
        return name != null ? name : "";
    }
}
