package com.beadhouse.business.SortAndGetTop;

import com.beadhouse.DAO.BeadHouse.BeadhouseImageManageRepository;
import com.beadhouse.DAO.BeadHouse.BeadhouseInfoRepository;
import com.beadhouse.model.Beadhouse.BeadhouseImageManage;
import com.beadhouse.model.Beadhouse.BeadhouseInfo;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beadhouse on 17-7-1.
 */

@Service
public class BeadhouseSort {

    @Data
    class IndexPageInfo {
        private int id;
        private String fullName;
        private String briefDescription;
        private String imageUrl;
        private String imageDescription;
    }

    @Inject
    BeadhouseInfoRepository beadhouseInfoRepository;

    @Inject
    BeadhouseImageManageRepository beadhouseImageManageRepository;

    @Transactional(readOnly = true)
    public List<IndexPageInfo> getIndexPage() {
        int[] ids = {108, 184, 121};
        List<IndexPageInfo> res = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            res.add(getIndexInfofromId(ids[i]));
        }
        return res;
    }

    @Transactional(readOnly = true)
    public IndexPageInfo getIndexInfofromId(int id) {
        IndexPageInfo info = new IndexPageInfo();
        BeadhouseInfo baseinfo;
        BeadhouseImageManage imageManage;
        try {
            baseinfo = this.beadhouseInfoRepository.findOne(id);
            imageManage = this.beadhouseImageManageRepository.findByBeadhouseidOrderByImagePriorityAsc(id).get(0);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        info.setId(id);
        info.setFullName(baseinfo.getFullName());
        info.setBriefDescription(baseinfo.getDescription().substring(0, 90));
        info.setImageUrl(BeadhouseImageManage.imageUrl + "/" + String.valueOf(id) + "/" + imageManage.getImagePath());
        info.setImageDescription(imageManage.getImageDescription());
        return info;
    }

    @Transactional(readOnly = true)
    public Object getBeadhousePage(int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "score");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<BeadhouseInfo> infos = beadhouseInfoRepository.findAll(pageable);
        List<IndexPageInfo> res = new ArrayList<>();
        for (BeadhouseInfo info : infos) {
            res.add(getIndexInfofromId(info.getId()));
        }
        return res;
    }
}
