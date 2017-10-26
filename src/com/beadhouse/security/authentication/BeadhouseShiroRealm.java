package com.beadhouse.security.authentication;

import com.beadhouse.DAO.Admin.AdminInfoRepostory;
import com.beadhouse.model.admin.AdminInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
public class BeadhouseShiroRealm extends AuthorizingRealm {
    @Inject
    AdminInfoRepostory adminInfoRepostory;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.fromRealm(getName()).iterator().next();
        if (userName != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            return info;
        }
        return null;
    }

    @Transactional
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName = usernamePasswordToken.getUsername();
        if (userName != null && !"".equals(userName)) {
            AdminInfo adminInfo = this.adminInfoRepostory.findByUsername(userName);
            if (adminInfo != null) {
                return new SimpleAuthenticationInfo(adminInfo.getUsername(),
                        adminInfo.getPassword(), getName());
            }
        }
        return null;
    }
}
