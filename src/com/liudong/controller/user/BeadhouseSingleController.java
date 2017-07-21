package com.liudong.controller.user;

import com.liudong.business.beadhousebusiness.BeadhouseSinglePage;
import com.liudong.model.Beadhouse.BeadhouseImageManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liudong on 17-7-2.
 */

@Controller
@RequestMapping(value = "/beadhousesinglepage")
public class BeadhouseSingleController {
    @Inject
    BeadhouseSinglePage singlePage;

    @RequestMapping(value = "getdata", method = RequestMethod.GET)
    @ResponseBody
    public Object getSinglePageData(HttpServletRequest request) {
        String beadhouseId = (String) request.getSession().getAttribute("beadhouseId");
        if (beadhouseId == null || beadhouseId.length() == 0) {
            return false;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("baseinfo", this.singlePage.getBaseInfo(Integer.valueOf(beadhouseId)));
        res.put("imagesinfo", this.singlePage.getImageInfo(Integer.valueOf(beadhouseId)));
        res.put("imagePrefix", BeadhouseImageManage.imageUrl + "/" + String.valueOf(beadhouseId) + "/");
        return res;
    }
}
