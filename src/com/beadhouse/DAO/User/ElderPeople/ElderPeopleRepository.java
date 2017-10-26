package com.beadhouse.DAO.User.ElderPeople;

import com.beadhouse.model.user.ElderPeople;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by beadhouse on 2017/1/9.
 */
public interface ElderPeopleRepository extends JpaRepository<ElderPeople, Integer> {
    List<ElderPeople> findByVipuserId(int vipuserid);

    ElderPeople findByIdNumber(String idNumber);

    ElderPeople findByid(int id);
}
