package com.beadhouse.DAO.User.VipUser;

import com.beadhouse.model.User.VipUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by beadhouse on 2016/12/20.
 */

@SuppressWarnings("unused")
public interface VipUserRepository extends JpaRepository<VipUser, Integer> {
    VipUser findByUserName(String name);

    VipUser findByEmailAddress(String emailAddress);

    VipUser findByTelephoneNumber(String phoneNumber);
}
