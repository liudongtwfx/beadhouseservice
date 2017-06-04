package com.liudong.DAO.User.ElderPeople;

import com.liudong.model.User.ElderPeople;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by liudong on 2017/1/9.
 */
@Repository
public class DefaultElderPeopleRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<ElderPeople> getByVipuserId(long vipuserid) {
        List<ElderPeople> list = this.entityManager.createQuery("select a from ElderPeople a where a.vipuserId=:vipuserid", ElderPeople.class)
                .setParameter("vipuserid", vipuserid).getResultList();
        return list;
    }
}
