package com.beadhouse.DAO.Generiac;

import com.beadhouse.DAO.Admin.AdminInfoRepostory;
import com.beadhouse.DAO.BeadHouse.BeadhouseAdministratorRepository;
import com.beadhouse.DAO.User.VipUser.VipUserRepository;
import com.beadhouse.model.beadhouse.BeadhouseAdministrator;
import com.beadhouse.model.user.VipUser;
import com.beadhouse.model.admin.AdminInfo;
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
 * Created by beadhouse on 2017/1/9.
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
        if (!password.equals(vipUser.getPassword()) && !BCrypt.checkpw(password, new String(vipUser.getPassword().getBytes(), StandardCharsets.UTF_8))) {
            log.warn("Authentication failed for user {}.", userName);
            return null;
        }
        log.debug("user {} successfully authenticated.", userName);
        return vipUser;
    }

    @Transactional
    public BeadhouseAdministrator authenticateBeadhouseAdmin(String userName, String password) {

        BeadhouseAdministrator administrator = this.administratorRepository.findByUserName(userName);
        if (administrator == null) {
            log.warn("Authentication failed for non-existence user {}.", userName);
            return null;
        }
        if (!password.equals(administrator.getPassword()) && !BCrypt.checkpw(password, new String(administrator.getPassword().getBytes(), StandardCharsets.UTF_8))) {
            log.warn("Authentication failed for user {}.", userName);
            return null;
        }
        log.debug("user {} successfully authenticated.", userName);
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
        log.debug("user {} successfully authenticated.", userName);
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
        admin.setPassword(getBCryptPassword(newPassword));
        this.administratorRepository.saveAndFlush(admin);
    }

    @Transactional
    public boolean savaAdmin(AdminInfo admin, String newPassword) {
        admin.setPassword(getBCryptPassword(newPassword));
        return this.admin.saveAndFlush(admin) != null;
    }

    public static String getBCryptPassword(String password) {
        if (password != null && password.length() > 0) {
            String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
            return BCrypt.hashpw(password, salt);
        }
        return "";
    }
}
