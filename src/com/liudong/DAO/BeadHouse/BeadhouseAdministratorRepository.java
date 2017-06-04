package com.liudong.DAO.BeadHouse;

import com.liudong.model.Beadhouse.BeadhouseAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liudong on 2017/1/17.
 */
public interface BeadhouseAdministratorRepository extends JpaRepository<BeadhouseAdministrator, Integer> {
    BeadhouseAdministrator findByUserName(String userName);

    BeadhouseAdministrator findByEmailAddress(String emailAddress);

    BeadhouseAdministrator findByTelephoneNumber(String telephoneNumber);
}
