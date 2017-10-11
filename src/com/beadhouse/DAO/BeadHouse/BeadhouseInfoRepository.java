package com.beadhouse.DAO.BeadHouse;

import com.beadhouse.model.Beadhouse.BeadhouseInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by beadhouse on 2017/2/27.
 */
public interface BeadhouseInfoRepository extends JpaRepository<BeadhouseInfo, Integer> {
    BeadhouseInfo findByFullName(String fullname);

    List<BeadhouseInfo> findByLocationId(String id);

    Page<BeadhouseInfo> findByLocationIdStartingWith(String provinceId, Pageable pageable);

    @Query("select p from BeadhouseInfo p where p.locationId like %?1")
    List<BeadhouseInfo> findByLocationIdLike(int provinceId);

    Page<BeadhouseInfo> findByFullNameContains(String content, Pageable pageable);
}
