package main.java.com.beadhouse.cache.addcachebusiness;

import com.google.gson.Gson;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseElderAccidentRepository;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseElderCheckinRepository;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseElderGoOutRepository;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseElderHealthRepository;
import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.cache.CacheManager;
import main.java.com.beadhouse.cache.CacheSource;
import main.java.com.beadhouse.cache.CacheStruct;
import main.java.com.beadhouse.cache.MaxMemoryExceedException;
import main.java.com.beadhouse.model.beadhouse.BeadhouseElderAccident;
import main.java.com.beadhouse.model.beadhouse.BeadhouseElderCheckin;
import main.java.com.beadhouse.model.beadhouse.BeadhouseElderGoOut;
import main.java.com.beadhouse.model.beadhouse.BeadhouseElderHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class BeadhouseManageCacheThread extends Thread {
    private int beadhouseId;
    private static long MAX_MEMORY = 10 * 1024 * 1024;

    @Autowired
    FetchDataFromMySQL fetchDataFromMySQL;

    public BeadhouseManageCacheThread() {
    }

    public void setBeadhouseId(int beadhouseId) {
        this.beadhouseId = beadhouseId;
    }

    @Override
    public void run() {
        try {
            addElderCheckinCache();
            addElderAccidentCache();
            addElderGooutCache();
            addElderHealthCache();
        } catch (MaxMemoryExceedException e) {
            replacement();
        }
    }

    private void replacement() {
    }

    public void addElderCheckinCache() {
        try {
            String cacheKey = "cache:beadhouseeldercheckin:" + beadhouseId;
            if (CacheManager.cacheExist(cacheKey)) {
                return;
            }
            CacheStruct cacheStruct = new CacheStruct();
            cacheStruct.setKey(cacheKey);
            List<BeadhouseElderCheckin> elderCheckinList = fetchDataFromMySQL.getElderCheckinList(beadhouseId);
            Gson gson = new Gson();
            cacheStruct.setValue(gson.toJson(elderCheckinList));
            cacheStruct.setScore(-1);
            cacheStruct.setLastVisitedTimeStamp(System.currentTimeMillis() / 1000);
            CacheManager cacheManager = new CacheManager();
            cacheManager.addCache(cacheStruct, 30 * 60);
            while (CacheManager.getUsedMemory(CacheSource.LOCAL) >= MAX_MEMORY) {
                replacement();
            }
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
            e.printStackTrace();
            LogType.EXCETPION.getLOGGER().info("add beahdouseeldercheckin cache error");
        }
    }

    private void addElderAccidentCache() {
        try {
            String cacheKey = "cache:beadhouseelderaccident:" + beadhouseId;
            if (CacheManager.cacheExist(cacheKey)) {
                return;
            }
            CacheStruct cacheStruct = new CacheStruct();
            cacheStruct.setKey(cacheKey);
            List<BeadhouseElderAccident> elderAccidentList = fetchDataFromMySQL.getElderAccidentList(beadhouseId);
            Gson gson = new Gson();
            cacheStruct.setValue(gson.toJson(elderAccidentList));
            cacheStruct.setScore(-1);
            cacheStruct.setLastVisitedTimeStamp(System.currentTimeMillis() / 1000);
            CacheManager cacheManager = new CacheManager();
            cacheManager.addCache(cacheStruct, 30 * 60);
            while (CacheManager.getUsedMemory(CacheSource.LOCAL) >= MAX_MEMORY) {
                replacement();
            }
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
            LogType.EXCETPION.getLOGGER().info("add beahdouseelderaccident cache error");
        }
    }

    private void addElderGooutCache() {
        try {
            String cacheKey = "cache:beadhouseeldergoout:" + beadhouseId;
            if (CacheManager.cacheExist(cacheKey)) {
                return;
            }
            CacheStruct cacheStruct = new CacheStruct();
            cacheStruct.setKey(cacheKey);
            List<BeadhouseElderGoOut> goOutList = fetchDataFromMySQL.getElderGooutList(beadhouseId);
            Gson gson = new Gson();
            cacheStruct.setValue(gson.toJson(goOutList));
            cacheStruct.setScore(-1);
            cacheStruct.setLastVisitedTimeStamp(System.currentTimeMillis() / 1000);
            CacheManager cacheManager = new CacheManager();
            cacheManager.addCache(cacheStruct, 30 * 60);
            while (CacheManager.getUsedMemory(CacheSource.LOCAL) >= MAX_MEMORY) {
                replacement();
            }
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
            LogType.EXCETPION.getLOGGER().info("add beahdouseelderaccident cache error");
        }
    }

    private void addElderHealthCache() {
        try {
            String cacheKey = "cache:beadhouseelderhealth:" + beadhouseId;
            if (CacheManager.cacheExist(cacheKey)) {
                return;
            }
            CacheStruct cacheStruct = new CacheStruct();
            cacheStruct.setKey(cacheKey);
            List<BeadhouseElderHealth> health = fetchDataFromMySQL.getElderHealthList(beadhouseId);
            Gson gson = new Gson();
            cacheStruct.setValue(gson.toJson(health));
            cacheStruct.setScore(-1);
            cacheStruct.setLastVisitedTimeStamp(System.currentTimeMillis() / 1000);
            CacheManager cacheManager = new CacheManager();
            cacheManager.addCache(cacheStruct, 30 * 60);
            while (CacheManager.getUsedMemory(CacheSource.LOCAL) >= MAX_MEMORY) {
                replacement();
            }
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
            LogType.EXCETPION.getLOGGER().info("add beahdouseelderhealth cache error");
        }
    }
}
