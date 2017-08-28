package com.liudong.config;

import com.liudong.business.kafkabusiness.kafkaConsumer.BeadhouseConsumer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by liudong on 2016/12/19.
 */
public class MyApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class, JpaConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        BeadhouseConsumer consumer = new BeadhouseConsumer("beadhousecomment");
        Thread thread = new Thread(consumer);
        thread.start();
        return new Class<?>[]{WebConfig.class, SecurityConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
