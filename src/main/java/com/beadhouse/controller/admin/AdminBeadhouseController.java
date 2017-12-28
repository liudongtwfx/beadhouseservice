package main.java.com.beadhouse.controller.admin;

import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseImageManageRepository;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseInfoRepository;
import main.java.com.beadhouse.model.beadhouse.BeadhouseImageManage;
import main.java.com.beadhouse.model.beadhouse.BeadhouseInfo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
 * Created by beadhouse on 2017/6/2.
 */

@Controller
@RequestMapping(value = "/admin/beadhouse")
@RequiresAuthentication
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
        String id = (String) request.getSession().getAttribute("beadhouseId");
        if (id == null || id.length() == 0) {
            return null;
        }
        BeadhouseInfo info = this.baseinfo.findOne(Integer.valueOf(id));
        List<BeadhouseImageManage> imagelist = this.imageManageRepository.findByBeadhouseid(Integer.valueOf(id));
        Map<String, Object> res = new HashMap<>();
        res.put("baseInfo", info);
        res.put("imageList", imagelist);
        res.put("imageBasePath", BeadhouseImageManage.imageUrl);
        return res;
    }

    @RequestMapping(value = "locationlist", method = RequestMethod.GET)
    @ResponseBody
    public Page<BeadhouseInfo> getProvinceId(HttpServletRequest request) {
        String provinceId = request.getParameter("provinceOrCityOrArea");
        if (provinceId == null || provinceId.length() != 6) {
            return null;
        }
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 0;
        int size = request.getParameter("size") != null ? Integer.parseInt(request.getParameter("size")) : 25;
        int endIndex = Integer.parseInt(request.getParameter("endIndex"));
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return this.baseinfo.findByLocationIdStartingWith(provinceId.substring(0, endIndex), pageable);
    }

    @RequestMapping(value = "beadhousename", method = RequestMethod.GET)
    @ResponseBody
    public Page<BeadhouseInfo> getByNameLike(HttpServletRequest request) {
        request.getRequestURI();
        String content = request.getParameter("searchContent");
        if (content == null || content.length() == 0) {
            return null;
        }
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 0;
        int size = request.getParameter("size") != null ? Integer.parseInt(request.getParameter("size")) : 25;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return this.baseinfo.findByFullNameContains(content, pageable);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public boolean beadhouseAddPost(HttpServletRequest request) {
        String beadhousename = request.getParameter("beadhousename");
        String invitecode = request.getParameter("invitecode");
        if (invitecode == null || beadhousename == null || beadhousename.length() == 0 || invitecode.length() == 0) {
            return false;
        }
        BeadhouseInfo info = new BeadhouseInfo();
        info.setFullName(beadhousename);
        info.setInviteCode(invitecode);
        this.baseinfo.save(info);
        return false;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String beadhouseAddGet(HttpServletRequest request) {
        return "admin/beadhouseadd";
    }
}
