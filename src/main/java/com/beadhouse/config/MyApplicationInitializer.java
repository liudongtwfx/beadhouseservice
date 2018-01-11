package main.java.com.beadhouse.config;

import main.java.com.beadhouse.business.kafkabusiness.kafkaConsumer.BeadhouseInfoConsumer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by beadhouse on 2016/12/19.
 */
public class MyApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class, JpaConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        BeadhouseInfoConsumer consumer = new BeadhouseInfoConsumer();
        Thread thread = new Thread(consumer);
        thread.start();
        return new Class<?>[]{WebConfig.class, SecurityConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
