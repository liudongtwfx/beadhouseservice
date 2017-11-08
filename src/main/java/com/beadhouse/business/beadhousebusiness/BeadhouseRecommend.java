package main.java.com.beadhouse.business.beadhousebusiness;

import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseImageManageRepository;
import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseInfoRepository;
import main.java.com.beadhouse.business.elasticsearchbusiness.BeadhouseRecomendES;
import main.java.com.beadhouse.model.beadhouse.BeadhouseImageManage;
import main.java.com.beadhouse.model.beadhouse.BeadhouseInfo;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class BeadhouseRecommend {
    @Inject
    BeadhouseInfoRepository beadhouseInfoRepository;
    @Inject
    BeadhouseImageManageRepository beadhouseImageManageRepository;

    public Object getBeadhouseRecommend(HttpServletRequest request) {
        String vipuser = (String) request.getSession().getAttribute("userName");
        List<Map<String, Object>> res = new ArrayList<>();
        SearchHits hits = new BeadhouseRecomendES().getBeadhouseByVIPUser(vipuser);
        if (hits != null) {
            for (SearchHit hit : hits) {
                res.add(hit.getSource());
            }
        }
        getRecommendFromOtherSource(res, vipuser);
        return res.subList(0, Math.min(res.size(), 3));
    }

    private void getRecommendFromOtherSource(List<Map<String, Object>> res, String vipuser) {
        if (res.size() >= 3) {
            return;
        }
        getBeadhouseRecommendFromBrowseLog(res, vipuser);
        getRecommendByBeadhouseScore(res);
    }

    private void getBeadhouseRecommendFromBrowseLog(List<Map<String, Object>> res, String vipuser) {
        if (res.size() > 3) {
            return;
        }
    }

    private void getRecommendByBeadhouseScore(List<Map<String, Object>> res) {
        if (res.size() > 3) {
            return;
        }
        Sort sort = new Sort(Sort.Direction.DESC, "score");
        Pageable pageable = new PageRequest(0, 6, sort);
        assert this.beadhouseInfoRepository != null;
        Page<BeadhouseInfo> beadhouseInfos = this.beadhouseInfoRepository.findAll(pageable);
        Set<Integer> existed = new HashSet<>();
        for (Map<String, Object> map : res) {
            if (map.containsKey("beadhouseId")) {
                existed.add((Integer) map.get("beadhouseId"));
            }
        }
        for (BeadhouseInfo info : beadhouseInfos) {
            if (!existed.contains(info.getId())) {
                Map<String, Object> addValue = new HashMap<>();
                List<BeadhouseImageManage> imageManage = this.beadhouseImageManageRepository.
                        findByBeadhouseidOrderByImagePriorityAsc(info.getId());
                addValue.put("id", info.getId());
                addValue.put("fullName", info.getFullName());
                addValue.put("briefDescription", info.getDescription());
                if (imageManage.size() > 0) {
                    addValue.put("imageUrl", BeadhouseImageManage.imageUrl + "/" + imageManage.get(0).getBeadhouseid() + "/" + imageManage.get(0).getImagePath());
                    addValue.put("imageDescription", imageManage.get(0).getImageDescription());
                }
                res.add(addValue);
            }
        }
    }
}
