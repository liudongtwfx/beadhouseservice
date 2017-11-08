package main.java.com.beadhouse.controller.template;


import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.model.user.VipUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.binding.expression.spel.SpringELExpression;
import org.springframework.binding.expression.spel.SpringELExpressionParser;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.webflow.expression.el.SpringBeanWebFlowELResolver;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@Controller
@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
public class TemplateDemo {
    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public String getDemo(Model model) {
        model.addAttribute("name", "liudong");
        VipUser vipUser = new VipUser();
        vipUser.setUserName("Dong Liu");
        model.addAttribute("vipuser", vipUser)
                .addAttribute("nationality", "China")
                .addAttribute("path", "/jpgfiles/logo.png")
                .addAttribute("customerName", " Dong Liu");
        return "demo";
    }

    @Value(value = "${me}")
    private String me;

    @RequestMapping(value = "/admin/common", method = RequestMethod.GET)
    public String getCommon() throws IOException {
        Resource resourcenew = new ClassPathResource("messages.properties");
        System.out.println(resourcenew.getFile().exists());
        Properties properties = PropertiesLoaderUtils.loadProperties(resourcenew);
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        LogType.DEBUGINFO.getLOGGER().debug(me);
        return "commonside";
    }
}
