package main.java.com.beadhouse.controller.dynamicbusiness.viewsontroller;


import main.java.com.beadhouse.DAO.Admin.HtmlViewRepository;
import main.java.com.beadhouse.model.admin.HtmlView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/views")
public class ViewListController {
    @Inject
    HtmlViewRepository htmlViewRepository;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String viewListPage() {
        return "html/viewlist";
    }

    @ModelAttribute(value = "viewlist")
    public List<HtmlView> htmlViewList() {
        return htmlViewRepository.findAll();
    }
}
