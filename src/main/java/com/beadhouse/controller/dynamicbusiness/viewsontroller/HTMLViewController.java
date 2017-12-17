package main.java.com.beadhouse.controller.dynamicbusiness.viewsontroller;

import main.java.com.beadhouse.DAO.Admin.HtmlViewRepository;
import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.business.dynamicbusiness.AddHtmlViewBusiness;
import main.java.com.beadhouse.dynamic.html.DomInfo;
import main.java.com.beadhouse.dynamic.html.areatemplate.AreaType;
import main.java.com.beadhouse.model.admin.HtmlView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/views/")
public class HTMLViewController {
    @Inject
    AddHtmlViewBusiness addHtmlViewBusiness;

    @Inject
    HtmlViewRepository htmlViewRepository;

    @RequestMapping(value = {"edit/getnodesinfo"})
    @ResponseBody
    public Map<String, Object> editHTMLDom(HttpServletRequest request) throws Exception {
        String htmlPath = (String) request.getSession().getAttribute("viewname");
        if (htmlPath == null) {
            return new HashMap<>();
        }
        DomInfo domInfo = new DomInfo(htmlPath);
        domInfo.createNodesInfo();
        Map<String, Object> res = new HashMap<>();
        res.put("childlist", domInfo.getChildList());
        res.put("nodesinfo", domInfo.getNodeInfoMap());
        res.put("rootid", domInfo.getRootid());
        return res;
    }

    @RequestMapping(value = {"edit"}, method = RequestMethod.GET)
    public String getViewEdit(Model model) {
        model.addAttribute("areatype", AreaType.values());
        return "html/edit";
    }

    @RequestMapping(value = "edit/submitchanges", method = RequestMethod.POST)
    @ResponseBody
    public String submitChanges(HttpServletRequest request, Model model) throws IOException {
        String htmlPath = (String) request.getSession().getAttribute("viewname");
        if (htmlPath == null) {
            return "failed";
        }
        DomInfo domInfo = new DomInfo(htmlPath);
        domInfo.createNodesInfo();
        return domInfo.createHTMLTree(request);
    }

    @RequestMapping(value = "/renderpage")
    public String renderPage() {
        return "";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addPage(Model model) {
        model.addAttribute("view", new HtmlView());
        return "html/add";
    }

    @RequestMapping(value = "add", params = "submit", method = RequestMethod.POST)
    public String submitAdd(HtmlView view, BindingResult result, HttpServletRequest request, Model model) throws IOException {
        try {
            addHtmlViewBusiness.addnewHtml(view);
        } catch (Exception e) {
            model.addAttribute("callbackinfo", e.toString());
            model.addAttribute("view", view);
            return "html/add";
        }
        request.getSession().setAttribute("viewname", view.getFilepath());
        return "forward:/admin/views/edit";
    }

    @RequestMapping(value = "/edit", params = "viewid")
    public String getEditPageViewId(HttpServletRequest request) {
        try {
            String viewname = htmlViewRepository.findOne(Integer.valueOf(request.getParameter("viewid"))).getFilepath();
            request.getSession().setAttribute("viewname", viewname);
            return "redirect:/admin/views/edit";
        } catch (Exception e) {
            e.printStackTrace();
            LogType.EXCETPION.getLOGGER().error(e);
            return "";
        }
    }
}
