package main.java.com.beadhouse.controller.admin;

import main.java.com.beadhouse.business.beadhousebusiness.LeisuregroupBusiness.LeisuregroupBusiness;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin/leisuregroup")
@RequiresRoles({"admin_leisuregroup", "admin_root"})
public class LeisureGroupController {
    @Inject
    LeisuregroupBusiness leisuregroupBusiness;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Object addLeisureGroup(HttpServletRequest request) {
        return this.leisuregroupBusiness.addLeisureGroup(request);
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addLeisureGroupPage(HttpServletRequest request) {
        return "admin/leisuregroupadd";
    }

    @RequestMapping(value = "imagesupload", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadImages(HttpServletRequest request) {
        return this.leisuregroupBusiness.handleLeisuregroupImagesUpload(request);
    }
}
