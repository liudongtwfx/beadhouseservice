package com.liudong.controller;

import com.liudong.DAO.User.VipUser.VipUserRepository;
import com.liudong.model.User.VipUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudong on 2016/12/21.
 */
@Controller
public class IndexController {
    private static final String MAX_LONG_AS_STRING = "9223372036854775807";
    private VipUserRepository vipUserRepository;

    @Bean
    public ViewResolver cnViewResolver() {
        return new ContentNegotiatingViewResolver();
    }

    @Autowired
    public IndexController(VipUserRepository vipUserRepository) {
        this.vipUserRepository = vipUserRepository;
    }

    @RequestMapping(value = "/")
    public String index() {
        //System.out.println("index.html");
        return "../../index";
    }

    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Map<String, List<VipUser>> vipUsers() {
        Map<String, List<VipUser>> res = new HashMap<>();
        res.put("list", this.vipUserRepository.findAll());
        return res;
    }
}
