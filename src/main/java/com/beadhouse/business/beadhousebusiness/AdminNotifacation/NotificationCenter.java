package main.java.com.beadhouse.business.beadhousebusiness.AdminNotifacation;


import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.business.redisclient.RedisClientConnector;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class NotificationCenter {
    public static Object getNotification(HttpServletRequest request) {
        String adminName = (String) request.getSession().getAttribute("adminName");
        if (adminName == null || adminName.length() == 0) {
            return null;
        }
        String key = "notification:" + adminName;
        try {
            List<String> res = RedisClientConnector.getRedis().lrange(key, 0, -1);
            for (String s : res) {
                LogType.DEBUGINFO.getLOGGER().debug(s);
            }
            return res;
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e);
            return null;
        }
    }
}
