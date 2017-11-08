package main.java.com.beadhouse.business.beadhousebusiness.AuthorizationCenter;

import main.java.com.beadhouse.DAO.Admin.AdminInfoRepostory;
import main.java.com.beadhouse.DAO.Admin.AuthApplyInfoRepostory;
import main.java.com.beadhouse.System.AuthApplyStatus;
import main.java.com.beadhouse.model.admin.AdminInfo;
import main.java.com.beadhouse.model.admin.AuthApplyInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class AddNewApply {
    @Inject
    AuthApplyInfoRepostory authApplyInfoRepostory;
    @Inject
    AdminInfoRepostory adminInfoRepostory;
    private static Jedis redis = new Jedis("localhost");

    @Transactional
    public Object newAuthApply(HttpServletRequest request) {
        String adminName = (String) request.getSession().getAttribute("adminName");
        String applyDepartment = request.getParameter("department");
        String applyReason = request.getParameter("applyReason");
        AuthApplyInfo authApplyInfo = new AuthApplyInfo();
        authApplyInfo.setAddtime(new Date());
        authApplyInfo.setApplyReason(applyReason);
        authApplyInfo.setApplyAdminName(adminName);
        authApplyInfo.setApplyDepartment(applyDepartment);
        authApplyInfo.setDepartmentPass(AuthApplyStatus.TOBEAPPROVE.getStatus());
        authApplyInfo.setLeaderPass(AuthApplyStatus.TOBEAPPROVE.getStatus());
        this.authApplyInfoRepostory.save(authApplyInfo);
        AdminInfo info = this.adminInfoRepostory.findByUsername(adminName);
        AuthApplyNotification notification = new AuthApplyNotification(authApplyInfo, info.getDepartment());
        Thread thread = new Thread(notification);
        thread.start();
        return true;
    }
}
