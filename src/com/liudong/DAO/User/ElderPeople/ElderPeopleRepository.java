package com.liudong.DAO.User.ElderPeople;

import com.liudong.model.User.ElderPeople;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liudong on 2017/1/9.
 */
public interface ElderPeopleRepository extends JpaRepository<ElderPeople, Integer> {
    List<ElderPeople> findByVipuserId(int vipuserid);

    ElderPeople findByIdNumber(String idNumber);

    ElderPeople findByid(int id);
}
