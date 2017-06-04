package com.liudong.DAO.Admin;

import com.liudong.model.admin.AdminInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liudong on 2017/5/26.
 */
public interface AdminInfoRepostory extends JpaRepository<AdminInfo, Integer> {
    AdminInfo findByUsername(String name);

    AdminInfo findByEmailAddress(String address);

    AdminInfo findByTelephoneNumber(String number);
}
