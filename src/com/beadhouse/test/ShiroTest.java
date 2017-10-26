package com.beadhouse.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class ShiroTest {
    @Test
    public void testShiro() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("file:shiro.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("liudong", "liu@6820138");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println(e);
        }
        subject.checkPermission("user:create");
        subject.login(token);
    }
}
