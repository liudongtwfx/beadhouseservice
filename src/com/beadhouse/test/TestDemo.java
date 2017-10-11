package com.beadhouse.test;

import com.beadhouse.System.SystemLogAspect;
import com.beadhouse.controller.admin.AdminCarouselController;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.NumberFormat;

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
}
