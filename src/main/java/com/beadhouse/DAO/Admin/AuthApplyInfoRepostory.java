package main.java.com.beadhouse.DAO.Admin;

import main.java.com.beadhouse.model.admin.AuthApplyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthApplyInfoRepostory extends JpaRepository<AuthApplyInfo, Integer> {

}
