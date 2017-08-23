package com.liudong.business.beadhousebusiness.BeadhouseCommentBuisiness;

import com.liudong.DAO.Admin.BeadhouseCommentRepository;
import com.liudong.model.admin.BeadhouseComment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class GetScoreThread implements Runnable {
    private final static Logger LOGGER = LogManager.getLogger("exception");
    private static final String GET_SCORE = "http://127.0.0.1:8000/getcommentscore?sentence=";
    private BeadhouseComment comment;
    private String content;
    BeadhouseCommentRepository commentRepository;

    public GetScoreThread(BeadhouseComment comment, String content, BeadhouseCommentRepository commentRepository) {
        this.comment = comment;
        this.content = content;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run() {
        float score = 0;
        try {
            score = getCommentScore(content);
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
            LOGGER.info("=====Excetpion=====");
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
