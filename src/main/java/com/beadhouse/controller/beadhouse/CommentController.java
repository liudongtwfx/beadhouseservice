package main.java.com.beadhouse.controller.beadhouse;


import main.java.com.beadhouse.business.beadhousebusiness.BeadhouseCommentBuisiness.BeadhouseCommentBusiness;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "beadhouse/comment")
public class CommentController {
    @Inject
    BeadhouseCommentBusiness business;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getCommentData(HttpServletRequest request) {
        String beadhouseId = String.valueOf(request.getSession().getAttribute("beadhouseId"));
        if (beadhouseId == null) {
            return null;
        }
        List<Map<String, Object>> res = new ArrayList<>();
        try {
            SearchHits comments = this.business.getCommentNotReply(Integer.valueOf(beadhouseId));
            for (SearchHit hit : comments) {
                Map<String, Object> source = hit.getSource();
                if (source.containsKey("anoymous") && source.get("anoymous").equals(false)) {
                    source.remove("commentor");
                }
                res.add(source);
            }
        } catch (Exception ignored) {

        }
        return res;
    }
}
