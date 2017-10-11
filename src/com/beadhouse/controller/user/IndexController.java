package com.beadhouse.controller.user;

import com.beadhouse.DAO.BeadHouse.BeadhouseInfoRepository;
import com.beadhouse.DAO.User.VipUser.VipUserRepository;
import com.beadhouse.business.SortAndGetTop.BeadhouseSort;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by beadhouse on 2016/12/21.
 */
@Controller
public class IndexController {
    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    @Inject
    VipUserRepository vipUserRepository;

    @Inject
    BeadhouseSort beadhouseSort;

    @Inject
    BeadhouseInfoRepository beadhouseInfoRepository;

    @Bean
    public ViewResolver cnViewResolver() {
        return new ContentNegotiatingViewResolver();
    }


    @RequestMapping(value = "/")
    public String index() {
        return "../../index";
    }

    @RequestMapping(value = "/beadhouselist", method = RequestMethod.GET)
    public String beadhouseListPage() {
        return "user/beadhouselist";
    }

    @RequestMapping(value = "/index/beadhouseinfo", method = RequestMethod.GET)
    @ResponseBody
    public Object getIndexBeadhouseInfo() {
        return this.beadhouseSort.getIndexPage();
    }

    @RequestMapping(value = "/beadhouselist/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getBeadhouseList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "18") Integer size) {
        return this.beadhouseSort.getBeadhousePage(page, size);
    }

    @RequestMapping(value = "/beadhousesingle", method = RequestMethod.GET)
    public String beadhouseSinglePage(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("beadhouseid");
        if (id == null || id.length() == 0) {
            return null;
        }
        request.getSession().setAttribute("beadhouseId", id);
        response.addCookie(new Cookie("beadhousescore", String.valueOf(this.beadhouseInfoRepository.findOne(Integer.valueOf(id)).getScore())));
        return "user/beadhousesingle";
    }

    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public String getArticlesPage() {
        return "user/articles";
    }

    @RequestMapping(value = "/articles/single", method = RequestMethod.GET)
    public String getSingleArticlePage(HttpServletRequest request, HttpServletResponse response) {
        String articleId = request.getParameter("articleId");
        request.getSession().setAttribute("articleId", articleId);
        return "user/singlearticle";
    }
}
