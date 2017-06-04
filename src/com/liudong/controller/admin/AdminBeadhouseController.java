package com.liudong.controller.admin;

import com.liudong.DAO.BeadHouse.BeadhouseImageManageRepository;
import com.liudong.DAO.BeadHouse.BeadhouseInfoRepository;
import com.liudong.model.Beadhouse.BeadhouseImageManage;
import com.liudong.model.Beadhouse.BeadhouseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudong on 2017/6/2.
 */

@Controller
@RequestMapping(value = "/admin/beadhouse")
public class AdminBeadhouseController {
    @Inject
    BeadhouseInfoRepository baseinfo;

    @Inject
    BeadhouseImageManageRepository imageManageRepository;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Page<BeadhouseInfo> getList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "15") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return this.baseinfo.findAll(pageable);
    }

    @RequestMapping(value = "single", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getOneSingleInfo(HttpServletRequest request) {
        String id = request.getParameter("beadhouseId");
        if (id == null || id.length() == 0) {
            return null;
        }
        BeadhouseInfo info = this.baseinfo.findOne(Integer.valueOf(id));
        List<BeadhouseImageManage> imagelist = this.imageManageRepository.findByBeadhouseid(Integer.valueOf(id));
        Map<String, Object> res = new HashMap<>();
        res.put("baseInfo", info);
        res.put("imageList", imagelist);
        return res;
    }
}
