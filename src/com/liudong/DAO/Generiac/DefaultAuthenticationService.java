package com.liudong.DAO.Generiac;

import com.liudong.DAO.Admin.AdminInfoRepostory;
import com.liudong.DAO.BeadHouse.BeadhouseAdministratorRepository;
import com.liudong.DAO.User.VipUser.VipUserRepository;
import com.liudong.model.Beadhouse.BeadhouseAdministrator;
import com.liudong.model.User.VipUser;
import com.liudong.model.admin.AdminInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by liudong on 2017/1/9.
 */
@Service
public class DefaultAuthenticationService {
    private static final Logger log = LogManager.getLogger();
    private static final SecureRandom RANDOM;
    private static final int HASHING_ROUNDS = 10;

    static {
        try {
            RANDOM = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException();
        }
    }

    @Inject
    VipUserRepository vipUserRepository;
    @Inject
    BeadhouseAdministratorRepository administratorRepository;
    @Inject
    AdminInfoRepostory admin;

    @Transactional
    public VipUser authenticateVipUser(String userName, String password) {

        VipUser vipUser = this.vipUserRepository.findByUserName(userName);
        if (vipUser == null) {
            log.warn("Authentication failed for non-existence user {}.", userName);
            return null;
        }
        if (!BCrypt.checkpw(password, new String(vipUser.getPassword().getBytes(), StandardCharsets.UTF_8))) {
            log.warn("Authentication failed for user {}.", userName);
            return null;
        }
        log.debug("User {} successfully authenticated.", userName);
        return vipUser;
    }

    @Transactional
    public BeadhouseAdministrator authenticateBeadhouseAdmin(String userName, String password) {

        BeadhouseAdministrator administrator = this.administratorRepository.findByUserName(userName);
        if (administrator == null) {
            log.warn("Authentication failed for non-existence user {}.", userName);
            return null;
        }
        if (!BCrypt.checkpw(password, new String(administrator.getPassword().getBytes(), StandardCharsets.UTF_8))) {
            log.warn("Authentication failed for user {}.", userName);
            return null;
        }
        log.debug("User {} successfully authenticated.", userName);
        return administrator;
    }

    @Transactional
    public AdminInfo authenticateAdmin(String userName, String password) {

        AdminInfo admin = this.admin.findByUsername(userName);
        if (admin == null) {
            log.warn("Authentication failed for non-existence user {}.", userName);
            return null;
        }
        if (!BCrypt.checkpw(password, new String(admin.getPassword().getBytes(), StandardCharsets.UTF_8))) {
            log.warn("Authentication failed for user {}.", userName);
            return null;
        }
        log.debug("User {} successfully authenticated.", userName);
        return admin;
    }

    @Transactional
    public void saveUser(VipUser user, String newPassword) {
        if (newPassword != null && newPassword.length() > 0) {
            String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
            user.setPassword(BCrypt.hashpw(newPassword, salt));
        }
        this.vipUserRepository.saveAndFlush(user);
    }

    @Transactional
    public void savaBeadhouseAdmin(BeadhouseAdministrator admin, String newPassword) {
        if (newPassword != null && newPassword.length() > 0) {
            String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
            admin.setPassword(BCrypt.hashpw(newPassword, salt));
        }
        this.administratorRepository.saveAndFlush(admin);
    }

    @Transactional
    public boolean savaAdmin(AdminInfo admin, String newPassword) {
        if (newPassword != null && newPassword.length() > 0) {
            String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
            admin.setPassword(BCrypt.hashpw(newPassword, salt));
        }
        return this.admin.saveAndFlush(admin) != null;
    }
}
