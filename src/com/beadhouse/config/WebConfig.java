package com.beadhouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by beadhouse on 2016/12/21.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.beadhouse.System", "com.beadhouse.controller"})
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    public WebConfig() {
        System.out.println("======webconfig initialing=======" + System.currentTimeMillis());
    }

    @Bean
    public ViewResolver cnViewResolver() {
        return new ContentNegotiatingViewResolver();
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/cssfiles/**")
                .addResourceLocations("/WEB-INF/cssfiles/");
        registry.addResourceHandler("/jsfiles/**")
                .addResourceLocations("/WEB-INF/jsfiles/");
        registry.addResourceHandler("/views/**")
                .addResourceLocations("/WEB-INF/views/");
        registry.addResourceHandler("/jpgfiles/**")
                .addResourceLocations("/WEB-INF/jpgfiles/");
        registry.addResourceHandler("/bootstrap/**")
                .addResourceLocations("/WEB-INF/bootstrap/");
        registry.addResourceHandler("/imagefiles/**")
                .addResourceLocations("/WEB-INF/imagefiles/");

    }
}

//@ComponentScan(basePackages = {"com.beadhouse.controller","com.beadhouse.System"},includeFilters = {@ComponentScan.Filter({Aspect.class, Controller.class})})
