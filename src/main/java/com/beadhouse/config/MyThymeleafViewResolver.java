package main.java.com.beadhouse.config;

import org.springframework.web.servlet.View;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.util.Locale;

public class MyThymeleafViewResolver extends ThymeleafViewResolver {

    private static final String prefix = "/WEB-INF/templates/";
    private static final String suffix = ".html";

    @Override
    protected View loadView(String viewName, Locale locale) throws Exception {
        String resourceName = prefix + viewName + suffix;
        try {
            this.getApplicationContext().getResource(resourceName).getInputStream();
        } catch (final Exception e) {
            if (logger.isDebugEnabled()) {
                if (logger.isTraceEnabled()) {
                    logger.trace("template view name:" + viewName + " {} is not existed");
                } else {
                    logger.debug("template view name:" + viewName + " {} is not existed");
                }
            }
            return null;
        }
        return super.loadView(viewName, locale);
    }
}
