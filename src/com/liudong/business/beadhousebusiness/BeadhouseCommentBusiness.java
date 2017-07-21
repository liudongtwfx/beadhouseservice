package com.liudong.business.beadhousebusiness;

import com.liudong.DAO.Admin.BeadhouseCommentRepository;
import com.liudong.model.admin.BeadhouseComment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.util.Date;

/**
 * Created by liudong on 17-7-14.
 */

@Service
public class BeadhouseCommentBusiness {

    private final static Logger LOGGER = LogManager.getLogger("exception");
    private static final String GET_SCORE = "http://127.0.0.1:8000/getcommentscore?sentence=";
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
        comment.setBeadhosueid(Integer.valueOf(beadhouseid));
        comment.setContent(commentContent);
        comment.setAnonymous(Boolean.valueOf(request.getParameter("anonymous")));
        comment.setAddtime(new Date());
        float score = 0;
        try {
            score = getCommentScore(request.getParameter("content"));
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement element : stackTrace) {
                sb.append(element.getClassName() + " " + String.valueOf(element.getLineNumber()) + " " + element.getMethodName());
                sb.append("\n");
            }
            LOGGER.error(sb.toString());
            e.printStackTrace();
        }
        comment.setScore(score);
        try {
            this.commentRepository.save(comment);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return true;
    }

    private float getCommentScore(String content) {
        HttpURLConnection conn = null;
        PrintWriter out = null;
        BufferedReader in = null;
        float score = 0;
        try {
            // 建立连接
            content = URLEncoder.encode(content, "utf-8");
            String url = GET_SCORE + content;
            URL httpUrl = new URL(url);
            conn = (HttpURLConnection) httpUrl.openConnection();
            if (200 == conn.getResponseCode()) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int len = -1;
                while (-1 != (len = is.read(bytes))) {
                    baos.write(bytes, 0, len);
                    baos.flush();
                }
                score = Float.valueOf(baos.toString());
            }

        } catch (MalformedURLException e) {
            LOGGER.info("=====connection time out=====" + e.getMessage() + " " + (conn != null ? conn.getURL() : null));
        } catch (Exception e) {
            assert conn != null;
            LOGGER.info("=====Excetpion=====" + e.getMessage() + " " + conn.getURL() + " " + conn.getRequestMethod());
        } finally {
            try {
                //释放资源
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
                if (null != conn) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
        return score;
    }
}
