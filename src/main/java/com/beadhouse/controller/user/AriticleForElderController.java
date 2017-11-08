package main.java.com.beadhouse.controller.user;

import main.java.com.beadhouse.DAO.Admin.ArticleForElderRepository;
import main.java.com.beadhouse.business.SortAndGetTop.ArticleForElderMainBuseness;
import main.java.com.beadhouse.business.elasticsearchbusiness.ElasticsearchBase;
import main.java.com.beadhouse.model.admin.ArticleForElder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by beadhouse on 17-7-8.
 */

@Controller
@RequestMapping(value = "/articles")
public class AriticleForElderController {
    @Inject
    ArticleForElderMainBuseness articleForElderSort;
    @Inject
    ArticleForElderRepository articleForElderRepository;

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
        return this.articleForElderSort.getSingleArticle(request);
    }

    @RequestMapping(value = "toes", method = RequestMethod.GET)
    @ResponseBody
    public String dbtoes() throws UnsupportedEncodingException {
        List<ArticleForElder> articles = this.articleForElderRepository.findAll();
        ElasticsearchBase base = new ElasticsearchBase("demo", "article");
        int added = 0;
        for (ArticleForElder article : articles) {
            article.setContent(new String(article.getContent().getBytes("ISO-8859-1"), "UTF-8"));
            try {
                base.insertToEs(article.toString());
                added++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "added:" + added;
    }
}
