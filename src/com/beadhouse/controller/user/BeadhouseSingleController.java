package com.beadhouse.controller.user;

import com.beadhouse.System.LogType;
import com.beadhouse.business.beadhousebusiness.BeadhouseSinglePage;
import com.beadhouse.model.Beadhouse.BeadhouseImageManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by beadhouse on 17-7-2.
 */

@Controller
@RequestMapping(value = "/beadhousesinglepage")
public class BeadhouseSingleController {
    @Inject
    BeadhouseSinglePage singlePage;

    @RequestMapping(value = "getdata", method = RequestMethod.GET)
    @ResponseBody
    public Object getSinglePageData(HttpServletRequest request) {
        String beadhouseId = String.valueOf(request.getSession().getAttribute("beadhouseId"));
        if (beadhouseId == null || beadhouseId.length() == 0) {
            return false;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("baseinfo", this.singlePage.getBaseInfo(Integer.valueOf(beadhouseId)));
        res.put("imagesinfo", this.singlePage.getImageInfo(Integer.valueOf(beadhouseId)));
        res.put("imagePrefix", BeadhouseImageManage.imageUrl + "/" + String.valueOf(beadhouseId) + "/");
        StringBuilder logContent = new StringBuilder();
        String userName = (String) request.getSession().getAttribute("userName");
        logContent.append(request.getSession().getId()).append(" ");
        logContent.append(beadhouseId).append(" ");
        if (userName != null) {
            logContent.append(userName);
        } else {
            logContent.append("anonymous");
        }
        LogType.BEADHOUSEBROWSE.getLOGGER().info(logContent);
        return res;
    }
}
