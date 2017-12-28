package main.java.com.beadhouse.config;

import org.springframework.web.servlet.View;

import java.util.Locale;

public class MyAjaxViewResolver extends MyThymeleafViewResolver {
    private static final String prefix = "/WEB-INF/templates/";
    private static final String suffix = ".html";

    @Override
    protected View loadView(String viewName, Locale locale) throws Exception {
        String resourceName = prefix + viewName + suffix;
        try {
            this.getApplicationContext().getResource(resourceName).getInputStream();
        } catch (final Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("template view name:" + viewName + " {} is not existed");
            }
            return null;
        }
        return super.loadView(viewName, locale);
    }
}
