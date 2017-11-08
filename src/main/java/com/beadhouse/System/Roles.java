package main.java.com.beadhouse.System;

public enum Roles {
    ADMIN_ROOT("admin_root", 1),
    ADMIN_USER("admin_user", 2),
    ADMIN_BEADHOUSE("admin_beadhouse", 3),
    ADMIN_LEISUREGROUP("admin_leisuregroup", 4),
    ADMIN_SYSTEM("admin_system", 5),
    BEADHOUSE_ROOT("beadhouse_root", 6),
    BEADHOUSE_ELDER("beadhouse_elder", 7),
    BEADHOUSE_SYSTEM("beadhouse_system", 8),
    BEADHOUSE_INFO("beadhouse_info", 9),
    USER_GUEST("user_guest", 10),
    USER_VIP("user_vip", 11);
    private String roleName;
    private final int index;

    Roles(String roleName, int index) {
        this.index = index;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public int getIndex() {
        return this.index;
    }
}
