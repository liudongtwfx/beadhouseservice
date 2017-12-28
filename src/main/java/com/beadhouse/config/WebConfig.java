package main.java.com.beadhouse.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.spring4.webflow.view.AjaxThymeleafViewResolver;
import org.thymeleaf.spring4.webflow.view.FlowAjaxThymeleafView;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.nio.charset.StandardCharsets;

/**
 * Created by beadhouse on 2016/12/21.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"main.java.com.beadhouse.System", "main.java.com.beadhouse.controller"})
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    private ApplicationContext context;

    @Bean(name = "generalViewResolver")
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setOrder(3);
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
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

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(-1);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setBasenames("classpath:messages");
        return messageSource;
    }

    @Bean
    public TemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setMessageSource(messageSource());
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(context);
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setSuffix(".html");
        resolver.setOrder(1);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCacheable(false);
        return resolver;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new MyThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setOrder(0);
        resolver.setCache(false);
        resolver.setViewClass(ThymeleafView.class);
        return resolver;
    }

    @Bean
    public ThymeleafViewResolver AjaxThemeleafViewResolver() {
        AjaxThymeleafViewResolver viewResolver = new MyAjaxViewResolver();
        viewResolver.setViewClass(FlowAjaxThymeleafView.class);
        viewResolver.setOrder(2);
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setCache(false);
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
