package main.java.com.beadhouse.business.dynamicbusiness;


import main.java.com.beadhouse.DAO.dynamic.BusinessRepository;
import main.java.com.beadhouse.System.CommonFinalVariable;
import main.java.com.beadhouse.model.dynamic.Business;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class AddBusinessService {
    @Inject
    BusinessRepository businessRepository;

    @Transactional
    public String addNewBusiness(HttpServletRequest request) {
        Business business = new Business();
        business.setChineseName(request.getParameter("chinesename"));
        business.setEnglishName(request.getParameter("englishname"));
        business.setMybusinesscomment(request.getParameter("comment"));
        business.setURLPath(request.getParameter("urlpath"));
        business.setUpdateTime(new Date());
        try {
            int parentid = 0;
            if (request.getParameterMap().containsKey("parentname")) {
                Business parent = businessRepository.findByEnglishName(request.getParameter("parentname"));
                if (parent != null) {
                    parentid = parent.getId();
                }
            }
            business.setViewPath(buildHTMLFile(business));
            business.setParentBusinessId(parentid);
            businessRepository.save(business);
            return "seccess";
        } catch (Exception e) {
            return e.toString();
        }
    }

    private String buildHTMLFile(Business business) {
        String base = CommonFinalVariable.ABSOLUTE_FILE_PATH;
        if (business.getParentBusinessId() == 0) {
        }
        Document document = new Document(base);
        return "";
    }
}
