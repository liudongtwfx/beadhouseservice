package com.beadhouse.controller.admin;

import com.beadhouse.DAO.Common.CarouselRepository;
import com.beadhouse.business.filehandle.FileHandler;
import com.beadhouse.model.Common.Carousel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by beadhouse on 17-6-19.
 */

@Controller
@RequestMapping("admin/carousel")
public class AdminCarouselController {
    private static final Logger log = LogManager.getLogger("controller");

    @Inject
    CarouselRepository carouselRepository;

    @RequestMapping(value = "userIndexPage/upload", method = RequestMethod.POST)
    @ResponseBody
    public boolean userIndexCarousel(MultipartHttpServletRequest request) {
        log.debug(request.getRequestURL());
        for (Object entry : request.getParameterMap().entrySet()) {
            System.out.println(entry);
        }
        //if (request.getParameter("upload") == null) return false;
        Carousel carousel = new Carousel();
        if (!handleImageRequest(request, carousel, "userIndexPage")) {
            return false;
        }
        carousel.setLocation("userIndexPage");
        try {
            log.debug(carousel);
            this.carouselRepository.save(carousel);
        } catch (Exception e) {
            System.out.println(e);
            log.error(e);
            return false;
        }
        return true;
    }

    @RequestMapping(value = "userIndexPage/list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserIndexCarousel(HttpServletRequest request) {
        log.debug(request.getRequestURI());
        Map<String, Object> res = new HashMap<>();
        try {
            res.put("imageList", this.carouselRepository.findByLocation("userIndexPage"));
        } catch (Exception e) {
            System.out.println(e);
            return res;
        }
        res.put("prefixUrl", Carousel.imageUrl);
        return res;
    }

    private boolean handleImageRequest(HttpServletRequest request, Carousel carousel, String location) {
        MultipartHttpServletRequest multirequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multirequest.getFile("upload");
        String des = Carousel.realPath + FileHandler.slash + location;
        log.debug(des);
        try {
            String newFileName = FileHandler.save(file, des);
            carousel.setPath(newFileName);
            carousel.setDescription(request.getParameter("description"));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
