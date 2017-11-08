package main.java.com.beadhouse.business.beadhousebusiness.ArticleForElderBusiness;

import main.java.com.beadhouse.DAO.Admin.AdminInfoRepostory;
import main.java.com.beadhouse.DAO.Admin.ArticleForElderRepository;
import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.model.admin.AdminInfo;
import main.java.com.beadhouse.model.admin.ArticleForElder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class ArticleForElderBusiness {
    @Inject
    ArticleForElderRepository articleForElderRepository;
    @Inject
    AdminInfoRepostory adminInfoRepostory;

    @Transactional
    public boolean addArticleForElder(HttpServletRequest request) {
        String name = (String) request.getSession().getAttribute("adminName");
        AdminInfo admin = this.adminInfoRepostory.findByUsername(name);
        ArticleForElder article = new ArticleForElder();
        article.setContent(request.getParameter("content"));
        article.setTitle(request.getParameter("title"));
        article.setSourceurl(request.getParameter("source"));
        article.setArticletag(request.getParameter("tags"));
        article.setAddtime(new Date());
        article.setStrategytype(1);
        article.setOperator(admin.getId());
        try {
            this.articleForElderRepository.save(article);
            return true;
        } catch (Exception e) {
            for (StackTraceElement element : e.getStackTrace()) {
                LogType.EXCETPION.getLOGGER().error(element);
                return false;
            }
        }
        return false;
    }
}
