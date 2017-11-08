package main.java.com.beadhouse.DAO.Admin;

import main.java.com.beadhouse.model.admin.AdminInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by beadhouse on 2017/5/26.
 */
public interface AdminInfoRepostory extends JpaRepository<AdminInfo, Integer> {
    AdminInfo findByUsername(String name);

    AdminInfo findByEmailAddress(String address);

    AdminInfo findByTelephoneNumber(String number);
}
