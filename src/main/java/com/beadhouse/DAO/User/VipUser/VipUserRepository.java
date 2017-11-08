package main.java.com.beadhouse.DAO.User.VipUser;

import main.java.com.beadhouse.model.user.VipUser;
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
