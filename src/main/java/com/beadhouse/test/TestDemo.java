package main.java.com.beadhouse.test;

import main.java.com.beadhouse.System.SystemLogAspect;
import main.java.com.beadhouse.controller.admin.AdminCarouselController;
import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Properties;

/**
 * Created by beadhouse on 17-6-22.
 */
public class TestDemo {
    @Test
    public void getA() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SystemLogAspect.class);
        context.refresh();
        AdminCarouselController adminCarouselController = context.getBean(AdminCarouselController.class);
        adminCarouselController.userIndexCarousel(null);
    }

    @Test
    public void funTimeTest() {
        int a = 100;
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(4);
        long start = System.currentTimeMillis();
        System.out.println(format.format(a));
        System.out.println(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        System.out.println(format.format(a).replaceAll(",", ""));
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void testProperties() throws IOException {
        PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
        Resource resourcenew = new ClassPathResource("messages.properties");
        System.out.println(resourcenew.getFile().exists());
        Properties properties = PropertiesLoaderUtils.loadProperties(resourcenew);
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
