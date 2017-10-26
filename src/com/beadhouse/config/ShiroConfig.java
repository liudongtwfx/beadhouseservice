package com.beadhouse.config;

import com.beadhouse.security.authentication.BeadhouseShiroRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {
//    @Bean
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
//        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
//        bean.setSecurityManager(getSecurityManager());
//        bean.setLoginUrl("/admin/login");
//        bean.setUnauthorizedUrl("/admin/unauthorized");
//        Map<String, Filter> filters = new HashMap<>();
//        filters.put("authc", getFormAuthenticationFilter());
//        bean.setFilters(filters);
//        Map<String, String> chains = new HashMap<>();
//        chains.put("/admin/login", "authc");
//        bean.setFilterChainDefinitionMap(chains);
//        return bean;
//    }
//
//    @Bean
//    public SecurityManager getSecurityManager() {
//        DefaultSecurityManager manager = new DefaultSecurityManager();
//        manager.setRealms(getRealms());
//        manager.setSessionManager(getDefaultWebSessionManager());
//        manager.setCacheManager(getEhCacheManager());
//        return manager;
//    }
//
//    @Bean
//    public EhCacheManager getEhCacheManager() {
//        EhCacheManager manager = new EhCacheManager();
//        manager.setCacheManagerConfigFile("classpath:ehcache.xml");
//        return manager;
//    }
//
//    @Bean
//    public Collection<Realm> getRealms() {
//        Set<Realm> realms = new HashSet<>();
//        realms.add(new BeadhouseShiroRealm());
//        return realms;
//    }
//
//    @Bean
//    public SimpleCookie getSimpleCookie() {
//        SimpleCookie cookie = new SimpleCookie();
//        cookie.setHttpOnly(true);
//        cookie.setMaxAge(180000);
//        return cookie;
//    }
//
//    @Bean
//    public DefaultWebSessionManager getDefaultWebSessionManager() {
//        DefaultWebSessionManager manager = new DefaultWebSessionManager();
//        manager.setGlobalSessionTimeout(18000000);
//        manager.setDeleteInvalidSessions(true);
//        manager.setSessionValidationSchedulerEnabled(true);
//        manager.setSessionValidationScheduler(getSessionValidationScheduler());
//        manager.setSessionIdCookie(getSimpleCookie());
//        return manager;
//    }
//
//    @Bean
//    public SessionValidationScheduler getSessionValidationScheduler() {
//        QuartzSessionValidationScheduler scheduler = new QuartzSessionValidationScheduler();
//        scheduler.setSessionManager(getSessionManager());
//        return scheduler;
//    }
//
//    @Bean
//    public ValidatingSessionManager getSessionManager() {
//        DefaultSessionManager sessionManager = new DefaultSessionManager();
//        sessionManager.setGlobalSessionTimeout(1800000);
//        sessionManager.setDeleteInvalidSessions(true);
//        sessionManager.setSessionValidationScheduler(getSessionValidationScheduler());
//        sessionManager.setSessionDAO(getSessionDAO());
//        return sessionManager;
//    }
//
//    @Bean
//    public SessionDAO getSessionDAO() {
//        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
//        sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
//        sessionDAO.setSessionIdGenerator(getSessionIdGenerator());
//        return sessionDAO;
//    }
//
//    @Bean
//    public SessionIdGenerator getSessionIdGenerator() {
//        return new JavaUuidSessionIdGenerator();
//    }
//
//    @Bean
//    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public MethodInvokingFactoryBean getMethodInvokingFactoryBean() {
//        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
//        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
//        bean.setArguments(new Object[]{getSecurityManager()});
//        return bean;
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(getSecurityManager());
//        return advisor;
//    }
//
//    @Bean
//    public FormAuthenticationFilter getFormAuthenticationFilter() {
//        FormAuthenticationFilter filter = new FormAuthenticationFilter();
//        filter.setLoginUrl("/admin/login");
//        filter.setSuccessUrl("/admin/index");
//        filter.setUsernameParam("username");
//        filter.setPasswordParam("password");
//        return filter;
//    }
//    //TODO credentialsMatcher Realm
}
