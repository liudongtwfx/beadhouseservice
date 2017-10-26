package com.beadhouse.business.beadhousebusiness.AuthorizationCenter;

import com.beadhouse.System.LogType;
import com.beadhouse.business.redisclient.RedisClientConnector;
import com.beadhouse.model.admin.AuthApplyInfo;
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
        pipe.lpush("notification:" + dpLeaderName, sb.toString());
        pipe.sync();
    }
}
