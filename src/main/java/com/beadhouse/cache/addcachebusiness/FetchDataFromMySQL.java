package main.java.com.beadhouse.cache.addcachebusiness;

import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseElderAccidentRepository;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseElderCheckinRepository;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseElderGoOutRepository;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseElderHealthRepository;
import main.java.com.beadhouse.model.beadhouse.BeadhouseElderAccident;
import main.java.com.beadhouse.model.beadhouse.BeadhouseElderCheckin;
import main.java.com.beadhouse.model.beadhouse.BeadhouseElderGoOut;
import main.java.com.beadhouse.model.beadhouse.BeadhouseElderHealth;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class FetchDataFromMySQL {
    @Inject
    BeadhouseElderCheckinRepository elderCheckinRepository;
    @Inject
    BeadhouseElderAccidentRepository accidentRepository;
    @Inject
    BeadhouseElderGoOutRepository goOutRepository;
    @Inject
    BeadhouseElderHealthRepository healthRepository;

    @Transactional(readOnly = true)
    public List<BeadhouseElderCheckin> getElderCheckinList(int beadhouseId) {
        return elderCheckinRepository.findByBeadhouseId(beadhouseId);
    }

    @Transactional
    public List<BeadhouseElderAccident> getElderAccidentList(int beadhouseId) {
        return accidentRepository.findByBeadhouseId(beadhouseId);
    }

    @Transactional
    public List<BeadhouseElderGoOut> getElderGooutList(int beadhouseId) {
        return goOutRepository.findByBeadhouseId(beadhouseId);
    }

    @Transactional
    public List<BeadhouseElderHealth> getElderHealthList(int beadhouseId) {
        return healthRepository.findByBeadhouseId(beadhouseId);
    }
}
