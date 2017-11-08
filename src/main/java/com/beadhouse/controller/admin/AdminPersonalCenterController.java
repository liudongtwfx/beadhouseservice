package main.java.com.beadhouse.controller.admin;


import main.java.com.beadhouse.business.beadhousebusiness.AdminNotifacation.NotificationCenter;
import main.java.com.beadhouse.business.beadhousebusiness.AuthorizationCenter.AddNewApply;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin/personal")
public class AdminPersonalCenterController {
    @Inject
    AddNewApply addNewApply;

    @RequestMapping(value = "authorizationcenter", method = RequestMethod.GET)
    public String getAuthorozationPage() {
        return "admin/authorizationcenter";
    }

    @RequestMapping(value = "newapply", method = RequestMethod.POST)
    @ResponseBody
    public Object newApply(HttpServletRequest request) {
        return this.addNewApply.newAuthApply(request);
    }

    @RequestMapping(value = "getnotification", method = RequestMethod.GET)
    @ResponseBody
    public Object getNotification(HttpServletRequest request) {
        return NotificationCenter.getNotification(request);
    }
}
