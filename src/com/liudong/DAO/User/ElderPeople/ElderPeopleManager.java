package com.liudong.DAO.User.ElderPeople;

import com.liudong.model.User.ElderPeople;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by liudong on 2017/1/9.
 */
@Service
public class ElderPeopleManager {
    @Inject
    ElderPeopleRepository elderPeopleRepository;

    @Transactional
    public void add(ElderPeople elderPeople) {
        this.elderPeopleRepository.save(elderPeople);
    }

    @Transactional
    public List<ElderPeople> getElderList(int id) {
        return this.elderPeopleRepository.findByVipuserId(id);
    }
}
