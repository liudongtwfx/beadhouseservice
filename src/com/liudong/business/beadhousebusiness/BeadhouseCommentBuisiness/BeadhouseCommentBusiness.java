package com.liudong.business.beadhousebusiness.BeadhouseCommentBuisiness;

import com.liudong.DAO.Admin.BeadhouseCommentRepository;
import com.liudong.business.elasticsearchbusiness.BeadhouseCommentEs;
import com.liudong.model.admin.BeadhouseComment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by liudong on 17-7-14.
 */

@Service
public class BeadhouseCommentBusiness {

    private final static Logger LOGGER = LogManager.getLogger("exception");
    @Inject
    BeadhouseCommentRepository commentRepository;

    @Transactional
    public boolean addComment(HttpServletRequest request) {
        BeadhouseComment comment = new BeadhouseComment();
        String beadhouseid = (String) request.getSession().getAttribute("beadhouseId");
        String userid = (String) request.getSession().getAttribute("userId");
        if (beadhouseid == null || userid == null || beadhouseid.length() == 0 || userid.length() == 0) {
            return false;
        }
        String commentContent = request.getParameter("content");
        comment.setCommentor(Integer.valueOf(userid));
        comment.setBeadhouseid(Integer.valueOf(beadhouseid));
        comment.setContent(commentContent);
        comment.setAnonymous(Boolean.valueOf(request.getParameter("anonymous")));
        comment.setAddtime(new Date());
        Thread getScore = new Thread(new GetScoreThread(comment, commentRepository));
        getScore.start();
        return true;
    }

    @Transactional
    public SearchHits getCommentNotReply(int beadhouseid) {
        BeadhouseCommentEs es = new BeadhouseCommentEs();
        try {
            SearchHits hits = es.getCommentByBeadhouseId(beadhouseid);
            return hits;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
