package main.java.com.beadhouse.config;

import org.springframework.context.annotation.Configuration;

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
