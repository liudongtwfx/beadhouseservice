package com.liudong.business.SortAndGetTop;

import com.liudong.DAO.BeadHouse.BeadhouseImageManageRepository;
import com.liudong.DAO.BeadHouse.BeadhouseInfoRepository;
import com.liudong.model.Beadhouse.BeadhouseImageManage;
import com.liudong.model.Beadhouse.BeadhouseInfo;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by liudong on 17-7-1.
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
        int[] ids = {108, 108, 108};
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
            if (this.beadhouseImageManageRepository == null) {
                System.out.println("base true");
            }
            if (this.beadhouseImageManageRepository == null) {
                System.out.println("image true");
            }
            baseinfo = this.beadhouseInfoRepository.findOne(id);
            imageManage = this.beadhouseImageManageRepository.findByBeadhouseidOrderByImagePriorityAsc(id).get(0);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        info.setId(id);
        info.setFullName(baseinfo.getFullName());
        info.setBriefDescription(baseinfo.getDescription());
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
