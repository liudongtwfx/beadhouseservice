package main.java.com.beadhouse.controller.user;

import main.java.com.beadhouse.business.beadhousebusiness.BeadhouseCommentBuisiness.BeadhouseCommentBusiness;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by beadhouse on 17-7-14.
 */

@Controller
@RequestMapping(value = "/beadhousecomment")
public class BeadhouseCommentController {

    @Inject
    BeadhouseCommentBusiness commentBusiness;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Object addBeadhouseComment(HttpServletRequest request) {
        return this.commentBusiness.addComment(request);
    }
}
