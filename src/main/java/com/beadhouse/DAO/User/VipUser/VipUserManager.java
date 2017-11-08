//package com.liudong.DAO.User.VipUser;
//
//import VipUser;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.inject.Inject;
//import java.util.List;
//
///**
// * Created by beadhouse on 2016/12/22.
// */
//@Service
//public class VipUserManager {
//    @Inject
//    VipUserRepository vipUserRepository;
//
//    @Transactional
//    public void addUser(VipUser user) {
//        this.vipUserRepository.add(user);
//    }
//
//    @Transactional
//    public List<VipUser> getAll() {
//        return this.vipUserRepository.getAll();
//    }
//
//    @Transactional
//    public boolean hasExist(VipUser user) {
//        VipUser findUser = this.vipUserRepository.getByName(user.getUserName());
//        return findUser != null;
//    }
//
//    @Transactional
//    public VipUser getUserByName(String name) {
//        return this.vipUserRepository.getByName(name);
//    }
//
//    @Transactional
//    public VipUser getUserByTelephoneNumber(String phoneNumber) {
//        return this.vipUserRepository.getByPhoneNumber(phoneNumber);
//    }
//
//    @Transactional
//    public VipUser getUserByEmailAddress(String emailAddress) {
//        return this.vipUserRepository.getByEmailAddress(emailAddress);
//    }
//}
