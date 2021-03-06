package com.liudong.controller.beadhouse;

import com.liudong.DAO.BeadHouse.BeadhouseAdministratorRepository;
import com.liudong.DAO.BeadHouse.BeadhouseElderHealthRepository;
import com.liudong.DAO.User.ElderPeople.ElderPeopleRepository;
import com.liudong.model.Beadhouse.BeadhouseElderHealth;
import com.liudong.model.User.ElderPeople;
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
 * Created by liudong on 2017/5/2.
 */

@Controller
@RequestMapping(value = "/beadhouse/elderhealth")
public class BeadhouseElderHealthController {
    @Inject
    BeadhouseElderHealthRepository healthRepository;

    @Inject
    BeadhouseAdministratorRepository beadhouseAdministrator;

    @Inject
    ElderPeopleRepository elderPeopleRepository;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public boolean addElderHealth(HttpServletRequest request) throws ParseException {
        BeadhouseElderHealth health = new BeadhouseElderHealth();
        updateLog(request, health);
        return this.healthRepository.save(health) != null;
    }


    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateElderHealth(HttpServletRequest request) throws ParseException {
        String idNum = request.getParameter("healthId");
        if (idNum == null || idNum.length() == 0) {
            return false;
        }
        BeadhouseElderHealth health = this.healthRepository.findOne(Integer.parseInt(idNum));
        updateLog(request, health);
        return this.healthRepository.save(health) != null;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public List<BeadhouseElderHealth> getHealthList(HttpServletRequest request) {
        int beadhouseId = (int) request.getSession().getAttribute("beadhouseId");
        List<BeadhouseElderHealth> list = this.healthRepository.findByBeadhouseId(beadhouseId);
        list.sort(Comparator.comparing(BeadhouseElderHealth::getExamingTime));
        return list;
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteElderHealth(HttpServletRequest request) {
        String healthId = request.getParameter("healthId");
        if (healthId == null || healthId.length() == 0) {
            return false;
        }
        this.healthRepository.delete(Integer.parseInt(healthId));
        return true;
    }

    private void updateLog(HttpServletRequest request, BeadhouseElderHealth health) throws ParseException {
        health.setElderIdNumber(request.getParameter("elderIdNumber"));
        health.setLowBloodPressure(Integer.parseInt(request.getParameter("bloodPressureLow")));
        health.setHighBloodPressure(Integer.parseInt(request.getParameter("bloodPressureHigh")));
        health.setHeartRate(Integer.parseInt(request.getParameter("heartRate")));
        health.setHealthStatus(request.getParameter("healthStatus"));
        health.setNursingResult(request.getParameter("nursingResult"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        health.setExamingTime(format.parse(request.getParameter("examingTime")));
        health.setBeadhouseId((Integer) request.getSession().getAttribute("beadhouseId"));
        health.setUpdateTime(format.parse(format.format(new Date())));
    }

    /**
     * this function is used for adding elderhealth_in logs
     */
    @RequestMapping(value = "addlogs", method = RequestMethod.GET)
    public void addLogs() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long epoch = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2016 00:00:00").getTime();
        long examingTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2017 00:00:00").getTime();
        long now = new Date().getTime();
        int[] ids = {2, 3, 7, 9, 10, 11};
        for (int i = 0; i < 200; i++) {
            ElderPeople elder = this.elderPeopleRepository.findByid(ids[(int) (Math.random() * 6)]);
            BeadhouseElderHealth health = new BeadhouseElderHealth();
            health.setElderIdNumber(elder.getIdNumber());
            health.setLowBloodPressure((int) (Math.random() * 20 + 70));
            health.setHighBloodPressure((int) (Math.random() * 70 + 110));
            health.setHeartRate((int) (Math.random() * 40 + 60));
            health.setHealthStatus("健康");
            health.setNursingResult("健康");
            health.setExamingTime(format.parse(format.format(new Date())));
            health.setBeadhouseId(108);
            health.setUpdateTime(format.parse(format.format(new Date())));
            this.healthRepository.save(health);
        }
    }
}
