package com.beadhouse.controller.admin;

import com.beadhouse.business.beadhousebusiness.LeisuregroupBusiness.LeisuregroupBusiness;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin/leisuregroup")
public class LeisureGroupController {
    @Inject
    LeisuregroupBusiness leisuregroupBusiness;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Object addLeisureGroup(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addLeisureGroupPage(HttpServletRequest request) {
        return "admin/addleisuregroup";
    }

    @RequestMapping(value = "imagesupload", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadImages(HttpServletRequest request) {
        return this.leisuregroupBusiness.handleLeisuregroupImagesUpload(request);
    }
}
