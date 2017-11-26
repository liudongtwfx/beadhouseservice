package main.java.com.beadhouse.controller.dynamicbusiness.viewsontroller;

import main.java.com.beadhouse.System.CommonFinalVariable;
import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.business.dynamicbusiness.AddHtmlViewBusiness;
import main.java.com.beadhouse.dynamic.dataencapsulation.StructAreaData;
import main.java.com.beadhouse.dynamic.html.DomInfo;
import main.java.com.beadhouse.dynamic.html.areatemplate.AreaType;
import main.java.com.beadhouse.model.admin.HtmlView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/views/")
public class HTMLViewController {
    @Inject
    AddHtmlViewBusiness addHtmlViewBusiness;

    @RequestMapping(value = {"edit/getnodesinfo"})
    @ResponseBody
    public Map<String, Object> editHTMLDom(HttpServletRequest request) throws Exception {
        String htmlPath = request.getSession().getAttribute("viewname") + ".html";
        File file = new File(CommonFinalVariable.ABSOLUTE_FILE_PATH + htmlPath);
        DomInfo domInfo = new DomInfo(file.getAbsolutePath());
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
        String htmlPath = request.getSession().getAttribute("viewname") + ".html";
        File file = new File(CommonFinalVariable.ABSOLUTE_FILE_PATH + htmlPath);
        DomInfo domInfo = new DomInfo(file.getAbsolutePath());
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
        return "redirect:/admin/views/edit";
    }
}
