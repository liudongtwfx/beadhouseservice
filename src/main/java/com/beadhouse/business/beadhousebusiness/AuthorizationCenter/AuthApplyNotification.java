package main.java.com.beadhouse.business.beadhousebusiness.AuthorizationCenter;

import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.business.CommonFunctions;
import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.model.admin.AuthApplyInfo;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class AuthApplyNotification extends Thread {
    private AuthApplyInfo authApplyInfo;
    private String adminDp;

    public AuthApplyNotification(AuthApplyInfo info, String adminDp) {
        authApplyInfo = info;
        this.adminDp = adminDp;
    }

    @Override
    public void run() {
        super.run();
        String dp = authApplyInfo.getApplyDepartment();
        StringBuilder sb = new StringBuilder();
        sb.append("authapply||")
                .append("adminName:").append(authApplyInfo.getApplyAdminName()).append("||")
                .append("applyReason:").append(authApplyInfo.getApplyReason()).append("||")
                .append("adminApplyDp:").append(dp);
        LogType.DEBUGINFO.getLOGGER().info(sb.toString());
        Jedis redis = RedisClientConnector.getRedis();
        String dpLeaderName = redis.hget("leaders", adminDp);
        LogType.DEBUGINFO.getLOGGER().info(dpLeaderName);
        Pipeline pipe = redis.pipelined();
        pipe.hset("notification:" + dpLeaderName, CommonFunctions.getRandomName(10), sb.toString());
        pipe.sync();
    }
}
