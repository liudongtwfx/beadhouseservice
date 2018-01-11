package main.java.com.beadhouse.controller.beadhouse;

import com.google.gson.Gson;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseAdministratorRepository;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseElderCheckinRepository;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseInfoRepository;
import main.java.com.beadhouse.DAO.User.ElderPeople.ElderPeopleRepository;
import main.java.com.beadhouse.cache.CacheManager;
import main.java.com.beadhouse.cache.CacheStruct;
import main.java.com.beadhouse.model.beadhouse.BeadhouseElderCheckin;
import main.java.com.beadhouse.model.beadhouse.BeadhouseInfo;
import main.java.com.beadhouse.model.user.ElderPeople;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by beadhouse on 2017/4/13.
 */
@Controller
@RequestMapping(value = "/beadhouse/eldercheckin")
public class BeadhouseElderCheckinController {
    @Inject
    BeadhouseAdministratorRepository beadhouseAdministrator;

    @Inject
    BeadhouseElderCheckinRepository beadhouseElderCheckinRepository;

    @Inject
    ElderPeopleRepository elderPeopleRepository;
    @Inject
    BeadhouseInfoRepository beadhouseInfoRepository;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public boolean addElderCheckin(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        BeadhouseElderCheckin beadhouseElderCheckin = new BeadhouseElderCheckin();
        updateCheckin(request, beadhouseElderCheckin);
        return this.beadhouseElderCheckinRepository.save(beadhouseElderCheckin) != null;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public boolean updateElderCheckin(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String checkinId = request.getParameter("checkinId");
        if (checkinId == null || checkinId.length() == 0) {
            return false;
        }
        BeadhouseElderCheckin beadhouseElderCheckin = this.beadhouseElderCheckinRepository.findOne(Integer.parseInt(checkinId));
        int beadhouseidSession = (int) request.getSession().getAttribute("beadhouseId");
        if (beadhouseElderCheckin == null || beadhouseElderCheckin.getBeadhouseId() != beadhouseidSession) {
            return false;
        }
        updateCheckin(request, beadhouseElderCheckin);
        return this.beadhouseElderCheckinRepository.saveAndFlush(beadhouseElderCheckin) != null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteElderCheckin(HttpServletRequest request, HttpServletResponse response) {
        String checkinId = request.getParameter("checkinId");
        if (checkinId == null || checkinId.length() == 0) {
            return false;
        }
        this.beadhouseElderCheckinRepository.delete(Integer.parseInt(checkinId));
        return true;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public List<BeadhouseElderCheckin> elderCheckinList(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String beadhouseId = String.valueOf(request.getSession().getAttribute("beadhouseId"));
        String cacheKey = "cache:beadhouseeldercheckin:" + beadhouseId;
        CacheStruct cacheStruct = new CacheManager().visitCache(cacheKey);
        if (cacheStruct != null) {
            return new Gson().fromJson(cacheStruct.getValue(), List.class);
        }
        List<BeadhouseElderCheckin> list = this.beadhouseElderCheckinRepository.findByBeadhouseId(Integer.parseInt(beadhouseId));
        list.sort(Comparator.comparing(BeadhouseElderCheckin::getCheckinTime));
        return list;
    }

    private void updateCheckin(HttpServletRequest request, BeadhouseElderCheckin beadhouseElderCheckin) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        beadhouseElderCheckin.setElderIdNumber(request.getParameter("elderId"));
        beadhouseElderCheckin.setBeadhouseId((Integer) request.getSession().getAttribute("beadhouseId"));
        beadhouseElderCheckin.setCheckinTime(format.parse(request.getParameter("eldercheckin")));
        if (request.getParameter("elderleavetime").length() > 0) {
            beadhouseElderCheckin.setLeaveTime(format.parse(request.getParameter("elderleavetime")));
        }
        if (request.getParameter("leavereason").length() > 0) {
            beadhouseElderCheckin.setLeaveReason(request.getParameter("leavereason"));
        }
        if (request.getParameter("extra").length() > 0) {
            beadhouseElderCheckin.setExtraContent(request.getParameter("extra"));
        }
        beadhouseElderCheckin.setPrincipleMan(request.getParameter("principle"));
    }

    /**
     * this function is used for adding eldercheck_in logs
     */
    @RequestMapping(value = "addlogs", method = RequestMethod.GET)
    public void addLogs() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long epoch = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2016 00:00:00").getTime();
        long checkinEnd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2017 00:00:00").getTime();
        //long leaveEnd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/2018 00:00:00").getTime();
        long now = new Date().getTime();
        List<ElderPeople> elderPeoplelist = this.elderPeopleRepository.findAll();
        List<BeadhouseInfo> beadhouseInfos = this.beadhouseInfoRepository.findAll();
        for (int i = 0; i < 2000; i++) {
            ElderPeople elder = elderPeoplelist.get((int) (Math.random() * elderPeoplelist.size()));
            BeadhouseElderCheckin checkin = new BeadhouseElderCheckin();
            long timemins = (long) (Math.random() * (checkinEnd - epoch));
            Date checkinDate = new Date(epoch + timemins);
            long end = (long) (epoch + timemins + Math.random() * 1000 * 60 * 60 * 24 * 60 + 1000 * 60 * 60 * 24 * 63);
            Date leaveDate = new Date(end);
            checkin.setElderIdNumber(elder.getIdNumber());
            checkin.setCheckinTime(format.parse(format.format(checkinDate)));
            checkin.setLeaveTime(format.parse(format.format(leaveDate)));
            checkin.setBeadhouseId(beadhouseInfos.get((int) (Math.random() * beadhouseInfos.size())).getId());
            checkin.setPrincipleMan("liu6891333");
            this.beadhouseElderCheckinRepository.save(checkin);
        }
        for (int i = 0; i < 100; i++) {
            ElderPeople elder = elderPeoplelist.get((int) (Math.random() * elderPeoplelist.size()));
            BeadhouseElderCheckin checkin = new BeadhouseElderCheckin();
            long timemins = (long) (Math.random() * (checkinEnd - epoch));
            Date checkinDate = new Date(epoch + timemins);
            long end = (long) (epoch + timemins + Math.random() * 1000 * 60 * 60 * 24 * 60 + 1000 * 60 * 60 * 24 * 63);
            Date leaveDate = new Date(end);
            checkin.setElderIdNumber(elder.getIdNumber());
            checkin.setCheckinTime(format.parse(format.format(checkinDate)));
            checkin.setLeaveTime(format.parse(format.format(leaveDate)));
            checkin.setBeadhouseId(108);
            checkin.setPrincipleMan("liu6891333");
            this.beadhouseElderCheckinRepository.save(checkin);
        }
    }
}
