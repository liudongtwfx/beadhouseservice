package com.liudong.test;

import com.liudong.System.SystemLogAspect;
import com.liudong.controller.admin.AdminCarouselController;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by liudong on 17-6-22.
 */
public class TestDemo {
    @Test
    public void getA() {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.register(SystemLogAspect.class);
        context.refresh();
        AdminCarouselController adminCarouselController=context.getBean(AdminCarouselController.class);
        adminCarouselController.userIndexCarousel(null);
    }
}
