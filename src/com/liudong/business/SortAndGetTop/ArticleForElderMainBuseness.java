package com.liudong.business.SortAndGetTop;

import com.liudong.DAO.Admin.ArticleForElderRepository;
import com.liudong.System.LogType;
import com.liudong.business.elasticsearchbusiness.ArticleESBusiness;
import com.liudong.model.admin.ArticleForElder;
import lombok.Data;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by liudong on 17-7-8.
 */

@Service
public class ArticleForElderMainBuseness {

    @Inject
    ArticleForElderRepository articleForElderRepository;

    @Data
    class BriefArticleInfo {
        int id;
        String title;
        Date addtime;
    }

    @Transactional(readOnly = true)
    public Object indexPageSort() {
        Sort sort = new Sort(Sort.Direction.DESC, "addtime");
        Pageable pageable = new PageRequest(0, 10, sort);
        return this.articleForElderRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Object articlePageSort(int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "addtime");
        Pageable pageable = new PageRequest(page, size, sort);
        Page<ArticleForElder> articles = this.articleForElderRepository.findAll(pageable);
        Map<String, Object> res = new HashMap<>();
        List<BriefArticleInfo> resList = new ArrayList<>();
        for (ArticleForElder article : articles) {
            BriefArticleInfo info = new BriefArticleInfo();
            info.setId(article.getId());
            info.setAddtime(article.getAddtime());
            info.setTitle(article.getTitle());
            resList.add(info);
        }
        res.put("list", resList);
        res.put("pageNumber", articles.getTotalPages());
        res.put("articleNumber", articles.getTotalElements());
        return res;
    }

    @Transactional(readOnly = true)
    public Object getSingleArticle(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String articleId = (String) session.getAttribute("articleId");
        if (articleId == null || articleId.length() == 0) {
            return false;
        }
        ArticleForElder article = null;
        try {
            article = this.articleForElderRepository.findOne(Integer.valueOf(articleId));
        } catch (Exception e) {
            System.out.println();
        }
        if (article == null) {
            return false;
        }
        Map<String, String> res = new HashMap<>();
        String content = null;
        try {
            content = new String(article.getContent().getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
        }
        res.put("title", article.getTitle());
        res.put("content", content);
        res.put("source", article.getSourceurl());
        res.put("tag", article.getArticletag());
        if (request.getSession().getAttribute("adminName") == null) {
            StringBuilder builder = new StringBuilder();
            String userName = (String) session.getAttribute("userName");
            if (userName == null) {
                userName = "anonymous";
            }
            builder.append(userName).append(" ").append(session.getId()).append(" ")
                    .append(article.getId()).append(" ")
                    .append(article.getTitle()).append(" ")
                    .append(article.getArticletag());
            LogType.ARTICLEBROWSE.getLOGGER().info(builder.toString());
        }
        return res;
    }

    public Object searchArticle(int page, int size, HttpServletRequest request) {
        String content = request.getParameter("searchContent");
        if (content == null || content.length() == 0) {
            return articlePageSort(page, size);
        }
        ArticleESBusiness articleESBusiness = new ArticleESBusiness();
        SearchHits hits = articleESBusiness.getArticleByDocId(content);
        if (hits == null) {
            return null;
        }
        List<Map<String, Object>> res = new ArrayList<>();
        for (int i = page * size; i <= Math.min(hits.totalHits, page * (size + 1)); i++) {
            Map<String, Object> map = new HashMap<>();
            SearchHit hit = hits.getHits()[i];
            for (Map.Entry<String, Object> entry : hit.getSource().entrySet()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return res;
    }
}
