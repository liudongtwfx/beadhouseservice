package com.beadhouse.controller.beadhouse;

import com.beadhouse.DAO.BeadHouse.BeadhouseAdministratorRepository;
import com.beadhouse.DAO.BeadHouse.BeadhouseElderAccidentRepository;
import com.beadhouse.DAO.BeadHouse.BeadhouseInfoRepository;
import com.beadhouse.DAO.User.ElderPeople.ElderPeopleRepository;
import com.beadhouse.model.Beadhouse.BeadhouseElderAccident;
import com.beadhouse.model.Beadhouse.BeadhouseInfo;
import com.beadhouse.model.User.ElderPeople;
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
 * Created by beadhouse on 2017/5/8.
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
    @Inject
    BeadhouseInfoRepository inRepository;

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

    @RequestMapping(value = "addTest", method = RequestMethod.GET)
    @ResponseBody
    public String addTestBatch() throws ParseException {
        int added = 0;
        List<ElderPeople> elderPeoplelist = this.elderPeopleRepository.findAll();
        List<BeadhouseInfo> beadhouseInfos = this.inRepository.findAll();
        String[] type = {"SuddenIllness", "Fracture", "Scald", "Bumps", "Extra"};
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long epoch = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2016 00:00:00").getTime();
        for (int i = 0; i < 2500; i++) {
            try {
                BeadhouseElderAccident elderAccident = new BeadhouseElderAccident();
                ElderPeople elder = elderPeoplelist.get((int) (Math.random() * elderPeoplelist.size()));
                elderAccident.setBeadhouseId(beadhouseInfos.get((int) (Math.random() * beadhouseInfos.size())).getId());
                elderAccident.setElderIdNumber(elder.getIdNumber());
                elderAccident.setReason("意外");
                long time = (long) (Math.random() * (new Date().getTime() - epoch)) + epoch;
                elderAccident.setHappenTime(format.parse(format.format(new Date(time))));
                elderAccident.setUpdateTime(format.parse(format.format(new Date())));
                elderAccident.setAccidentType(type[(int) (Math.random() * 5)]);
                this.accidentRepository.save(elderAccident);
                added += 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "added:" + added;
    }
}
