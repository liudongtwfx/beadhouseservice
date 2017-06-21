//package com.liudong.DAO.User.ElderPeople;
//
//import com.liudong.model.User.ElderPeople;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.inject.Inject;
//import java.sql.SQLDataException;
//import java.util.List;
//
///**
// * Created by liudong on 2017/1/9.
// */
//@Service
//public class ElderPeopleManager {
//    @Inject
//    ElderPeopleRepository elderPeopleRepository;
//
//    @Transactional(rollbackFor = SQLDataException.class)
//    public void add(ElderPeople elderPeople) {
//        this.elderPeopleRepository.save(elderPeople);
//    }
//
//    @Transactional(rollbackFor = SQLDataException.class)
//    public List<ElderPeople> getElderList(int id) {
//        return this.elderPeopleRepository.findByVipuserId(id);
//    }
//}
