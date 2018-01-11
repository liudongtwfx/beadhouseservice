package main.java.com.beadhouse.controller.dynamicbusiness.viewsontroller;

import main.java.com.beadhouse.System.FrontEventType;
import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.dynamic.action.Action;
import main.java.com.beadhouse.dynamic.action.ActionRelationShips;
import main.java.com.beadhouse.dynamic.html.DomInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/views")
public class AddEventSourceController {

    class ActionAndRelationShips {
        private List<Action> actions;
        private List<ActionRelationShips> relationShips;

        ActionAndRelationShips() {
            this.actions = new ArrayList<>();
            this.relationShips = new ArrayList<>();
        }
    }

    @RequestMapping(value = "addeventnodelist")
    public String addEventNodePage(HttpServletRequest request, ModelMap modelMap) {
        String viewname = (String) request.getSession().getAttribute("viewname");
        LogType.DEBUGINFO.getLOGGER().debug(viewname);
        if (viewname == null) {
            return "redirect:/admin/views/add";
        }
        DomInfo domInfo = new DomInfo(viewname);
        domInfo.createNodesInfo();
        modelMap.addAttribute("maybeevent", domInfo.filterButtonAndInput());
        return "html/addeventnodelist";
    }

    @RequestMapping(value = "addevent", params = "nodeid")
    public String addEventPage(HttpServletRequest request, ModelMap modelMap) {
        String viewname = (String) request.getSession().getAttribute("viewname");
        LogType.DEBUGINFO.getLOGGER().debug(viewname);
        if (viewname == null) {
            return "redirect:/admin/views/add";
        }
        request.getSession().setAttribute("nodeid", request.getParameter("nodeid"));
        modelMap.addAttribute("actionAndRelations", new ActionAndRelationShips());
        return "html/addeventsource";
    }

    @ModelAttribute(value = "frontEventType")
    public List<FrontEventType> getFrontEventType() {
        return Arrays.asList(FrontEventType.values());
    }

    @RequestMapping(value = "addevent", params = "addevent")
    public String addEvent(ActionAndRelationShips actionAndRelationShips,
                           HttpServletRequest request, ModelMap modelMap) {
        return "html/addeventsource";
    }
}
