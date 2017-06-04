package com.liudong.controller.beadhouse;

import com.liudong.DAO.BeadHouse.BeadhouseAdministratorRepository;
import com.liudong.DAO.BeadHouse.BeadhouseElderGoOutRepository;
import com.liudong.DAO.User.ElderPeople.ElderPeopleRepository;
import com.liudong.model.Beadhouse.BeadhouseElderGoOut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by liudong on 2017/5/6.
 */

@Controller
@RequestMapping(value = "/beadhouse/eldergoout")
public class BeadhouseElderGoOutController {
    @Inject
    BeadhouseElderGoOutRepository goOutRepository;

    @Inject
    BeadhouseAdministratorRepository beadhouseAdministrator;

    @Inject
    ElderPeopleRepository elderPeopleRepository;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public boolean addElderGoOut(HttpServletRequest request) throws ParseException {
        BeadhouseElderGoOut goout = new BeadhouseElderGoOut();
        updateLog(request, goout);
        return this.goOutRepository.save(goout) != null;
    }


    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateElderGoOut(HttpServletRequest request) throws ParseException {
        String idNum = request.getParameter("goOutId");
        if (idNum == null || idNum.length() == 0) {
            return false;
        }
        BeadhouseElderGoOut goout = this.goOutRepository.findOne(Long.parseLong(idNum));
        updateLog(request, goout);
        return this.goOutRepository.saveAndFlush(goout) != null;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public List<BeadhouseElderGoOut> getGoOutList(HttpServletRequest request) {
        int beadhouseId = (int) request.getSession().getAttribute("beadhouseId");
        List<BeadhouseElderGoOut> list = this.goOutRepository.findByBeadhouseId(beadhouseId);
        list.sort(Comparator.comparing(BeadhouseElderGoOut::getGoOutTime));
        return list;
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteElderGoOut(HttpServletRequest request) {
        String gooutId = request.getParameter("goOutId");
        if (gooutId == null || gooutId.length() == 0) {
            return false;
        }
        this.goOutRepository.delete(Long.parseLong(gooutId));
        return true;
    }

    private void updateLog(HttpServletRequest request, BeadhouseElderGoOut goOut) throws ParseException {
        goOut.setElderIdNumber(request.getParameter("elderId"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        goOut.setGoOutTime(format.parse(request.getParameter("elderGoOutTime")));
        String back = request.getParameter("elderBackTime");
        if (back != null && back.length() > 0) {
            goOut.setBackTime(format.parse(request.getParameter("elderBackTime")));
        }
        goOut.setReason(request.getParameter("elderGoOutReason"));
        goOut.setBeadhouseId((Integer) request.getSession().getAttribute("beadhouseId"));
        goOut.setUpdateTime(format.parse(format.format(new Date())));
    }
}
