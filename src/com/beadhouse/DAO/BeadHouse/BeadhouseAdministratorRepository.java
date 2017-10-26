package com.beadhouse.DAO.BeadHouse;

import com.beadhouse.model.beadhouse.BeadhouseAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by beadhouse on 2017/1/17.
 */
public interface BeadhouseAdministratorRepository extends JpaRepository<BeadhouseAdministrator, Integer> {
    BeadhouseAdministrator findByUserName(String userName);

    BeadhouseAdministrator findByEmailAddress(String emailAddress);

    BeadhouseAdministrator findByTelephoneNumber(String telephoneNumber);
}
