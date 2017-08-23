package com.liudong.controller.beadhouse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "beadhouse/comment")
public class BeadhouseCommentController {
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Map<String, Object> getCommentData(HttpServletRequest request) {
        if (request.getSession().getAttribute("beadhouseId") == null) {
            return null;
        }
        Map<String, Object> res = new HashMap<>();
        return res;
    }
}
