package com.liudong.controller.beadhouse;

import com.liudong.DAO.BeadHouse.BeadhouseAdministratorRepository;
import com.liudong.DAO.BeadHouse.BeadhouseElderAccidentRepository;
import com.liudong.DAO.User.ElderPeople.ElderPeopleRepository;
import com.liudong.model.Beadhouse.BeadhouseElderAccident;
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
 * Created by liudong on 2017/5/8.
 */

@Controller
@RequestMapping(value = "beadhouse/elderaccident")
public class BeadhouseElderAccidentController {
    @Inject
    BeadhouseElderAccidentRepository accidentRepository;

    @Inject
    BeadhouseAdministratorRepository beadhouseAdministrator;

    @Inject
    ElderPeopleRepository elderPeopleRepository;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public boolean addElderAccident(HttpServletRequest request) throws ParseException {
        BeadhouseElderAccident accident = new BeadhouseElderAccident();
        updateLog(request, accident);
        return this.accidentRepository.save(accident) != null;
    }


    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateElderAccident(HttpServletRequest request) throws ParseException {
        String idNum = request.getParameter("accidentId");
        if (idNum == null || idNum.length() == 0) {
            return false;
        }
        BeadhouseElderAccident accident = this.accidentRepository.findOne(Long.parseLong(idNum));
        updateLog(request, accident);
        return this.accidentRepository.saveAndFlush(accident) != null;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public List<BeadhouseElderAccident> getGoOutList(HttpServletRequest request) {
        int beadhouseId = (int) request.getSession().getAttribute("beadhouseId");
        List<BeadhouseElderAccident> list = this.accidentRepository.findByBeadhouseId(beadhouseId);
        list.sort(Comparator.comparing(BeadhouseElderAccident::getUpdateTime));
        return list;
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteElderGoOut(HttpServletRequest request) {
        String accidentId = request.getParameter("accidentId");
        if (accidentId == null || accidentId.length() == 0) {
            return false;
        }
        this.accidentRepository.delete(Long.parseLong(accidentId));
        return true;
    }

    private void updateLog(HttpServletRequest request, BeadhouseElderAccident accident) throws ParseException {
        accident.setElderIdNumber(request.getParameter("elderId"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        accident.setAccidentType(request.getParameter("elderAccidentType"));
        String reason = request.getParameter("elderAccidentReason");
        if (reason != null && reason.length() > 0) {
            accident.setReason(reason);
        }
        accident.setHappenTime(format.parse(request.getParameter("elderAccidentTime")));
        accident.setUpdateTime(format.parse(format.format(new Date())));
        accident.setBeadhouseId((Integer) request.getSession().getAttribute("beadhouseId"));
    }
}
