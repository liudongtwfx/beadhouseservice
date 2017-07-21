package com.liudong.controller.user;

import com.liudong.business.SortAndGetTop.ArticleForElderSort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liudong on 17-7-8.
 */

@Controller
@RequestMapping(value = "/articles")
public class AriticleForElderController {
    @Inject
    ArticleForElderSort articleForElderSort;

    @RequestMapping(value = "indexPage", method = RequestMethod.GET)
    @ResponseBody
    public Object getArticleIndexPage() {
        return this.articleForElderSort.indexPageSort();
    }

    @RequestMapping(value = "articlePage", method = RequestMethod.GET)
    @ResponseBody
    public Object getArticlePageInfo(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                     @RequestParam(value = "size", defaultValue = "15") Integer size) {
        return this.articleForElderSort.articlePageSort(page, size);
    }

    @RequestMapping(value = "singlearticle", method = RequestMethod.GET)
    @ResponseBody
    public Object getSingleArticleContent(HttpServletRequest request) {
        String articleId = (String) request.getSession().getAttribute("articleId");
        if (articleId == null || articleId.length() == 0) {
            return false;
        }
        return this.articleForElderSort.getSingleArticle(Integer.valueOf(articleId));
    }
}
