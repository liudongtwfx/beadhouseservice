package main.java.com.beadhouse.business;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class CommonFunctions {
    private static final String IPAddess;

    static {
        Enumeration allNetInterfaces = null;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress ip;
        String IPTEMP = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address && netInterface.getName().equals("enp2s0")) {
                    IPTEMP = ip.getHostAddress();
                }
            }
        }
        IPAddess = IPTEMP != null ? IPTEMP : "127.0.0.1";
    }

    public static Map<String, String> getFieldValue(String content) {
        String[] fields = content.split("\\|\\|");
        Map<String, String> fieldKV = new HashMap<>();
        for (String field : fields) {
            String[] kv = field.split(":", 2);
            fieldKV.put(kv[0], kv[1]);
        }
        return fieldKV;
    }

    public static String getIpAddress() {
        return IPAddess;
    }

    public static String getRandomName(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(base.charAt((int) (Math.random() * base.length())));
        }
        return sb.toString();
    }
}
