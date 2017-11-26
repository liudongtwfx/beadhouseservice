package main.java.com.beadhouse.controller.dynamicbusiness.viewsontroller;


import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.dataencapsulation.StructAreaData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/views")
public class AddDataSourceController {
    @RequestMapping(value = "adddatasource", method = RequestMethod.GET)
    public String addDataSourcePage(HttpServletRequest request) {
        String viewname = (String) request.getSession().getAttribute("viewname");
        if (viewname == null) {
            return "redirect:/admin/views/add";
        }
        if (!hasStructType(viewname)) {
            return "redirect:/admin/views/edit";
        }
        return "html/adddatasource";
    }

    @ModelAttribute(value = "elementlist")
    public List<StructAreaData> getStruct(HttpServletRequest request) {
        String viewname = (String) request.getSession().getAttribute("viewname");
        return new ArrayList<>();
    }

    private boolean hasStructType(String viewname) {
        Jedis redis = RedisClientConnector.getRedis();
        return redis.keys(viewname).size() != 0 && redis.hgetAll(viewname).size() != 0;
    }
}
