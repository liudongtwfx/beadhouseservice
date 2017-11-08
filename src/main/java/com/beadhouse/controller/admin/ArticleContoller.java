package main.java.com.beadhouse.controller.admin;

import main.java.com.beadhouse.business.SortAndGetTop.ArticleForElderMainBuseness;
import main.java.com.beadhouse.business.beadhousebusiness.ArticleForElderBusiness.ArticleForElderBusiness;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "admin/articleforelder")
public class ArticleContoller {
    @Inject
    ArticleForElderBusiness articleForElderBusiness;
    @Inject
    ArticleForElderMainBuseness articleForElderSort;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String getArtcileAddPage() {
        return "admin/articleforelderadd";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public boolean addArtcile(HttpServletRequest request) {
        return this.articleForElderBusiness.addArticleForElder(request);
    }

    @RequestMapping(value = "lists", method = RequestMethod.GET)
    public String getArticleListPage() {
        return "admin/articleforelderlist";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Object getArticleList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "15") Integer size) {
        return this.articleForElderSort.articlePageSort(page, size);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public Object articlesSearch(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "15") Integer size, HttpServletRequest request) {
        return this.articleForElderSort.searchArticle(page, size, request);
    }

    @RequestMapping(value = "singlepage", method = RequestMethod.GET)
    public Object articlesSearch(HttpServletRequest request) {
        String articleId = request.getParameter("articleId");
        request.getSession().setAttribute("articleId", articleId);
        return "admin/articlesinglepage";
    }

    @RequestMapping(value = "single", method = RequestMethod.GET)
    @ResponseBody
    public Object getArticleContent(HttpServletRequest request) {
        return this.articleForElderSort.getSingleArticle(request);
    }
}
