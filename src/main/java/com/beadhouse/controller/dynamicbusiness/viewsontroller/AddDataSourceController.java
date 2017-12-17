package main.java.com.beadhouse.controller.dynamicbusiness.viewsontroller;


import com.alibaba.fastjson.JSONArray;
import main.java.com.beadhouse.DAO.Admin.HtmlViewRepository;
import main.java.com.beadhouse.System.CommonFinalVariable;
import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.business.redisclient.RedisClientConnector;
import main.java.com.beadhouse.dynamic.dataencapsulation.StructAnalysis;
import main.java.com.beadhouse.dynamic.dataencapsulation.StructAreaData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/views")
public class AddDataSourceController {

    @Inject
    HtmlViewRepository htmlViewRepository;

    @RequestMapping(value = "adddatasource", method = RequestMethod.GET)
    public String addDataSourcePage(HttpServletRequest request) {
        String viewname = (String) request.getSession().getAttribute("viewname");
        LogType.DEBUGINFO.getLOGGER().debug(viewname);
        String absolute = CommonFinalVariable.ABSOLUTE_FILE_PATH + viewname + ".html";
        if (viewname == null) {
            return "redirect:/admin/views/add";
        }
        if (!hasStructType(absolute)) {
            return "redirect:/admin/views/edit";
        }
        return "html/adddatasource";
    }

    @ModelAttribute(value = "elementlist")
    public List<StructAreaData> getStruct(HttpServletRequest request) {
        String viewname = (String) request.getSession().getAttribute("viewname");
        String absolute = CommonFinalVariable.ABSOLUTE_FILE_PATH + viewname + ".html";
        if (viewname == null) {
            return new ArrayList<>();
        }
        return StructAnalysis.getStructAreaDatas(absolute);
    }

    private boolean hasStructType(String viewname) {
        Jedis redis = RedisClientConnector.getRedis();
        return redis.keys(viewname).size() != 0 && redis.hgetAll(viewname).size() != 0;
    }

    @RequestMapping(value = "adddatasource", params = "viewid")
    public String addDataSourcePageviewId(HttpServletRequest request) {
        try {
            String viewname = htmlViewRepository.findOne(Integer.valueOf(request.getParameter("viewid"))).getFilepath();
            request.getSession().setAttribute("viewname", viewname);
            return "redirect:/admin/views/adddatasource";
        } catch (Exception e) {
            e.printStackTrace();
            LogType.EXCETPION.getLOGGER().error(e);
            return "";
        }
    }

    @RequestMapping(value = "adddatasource/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDataSource(HttpServletRequest request) {
        String viewname = (String) request.getSession().getAttribute("viewname");
        if (viewname == null || viewname.equals("")) {
            return "false";
        }
        String absolute = CommonFinalVariable.ABSOLUTE_FILE_PATH + viewname + ".html";
        String schema = request.getParameter("schema");
        String table = request.getParameter("table");
        String elementId = request.getParameter("elementId");
        JSONArray array = JSONArray.parseArray(request.getParameter("columns"));
        List<String> columns = new ArrayList<>();
        array.forEach(c -> columns.add((String) c));
        LogType.DEBUGINFO.getLOGGER().debug("viewname:" + viewname + ",schema:" + schema + ",table:" + table + ",elementId:" + elementId);
        String value = StructAnalysis.analysis(elementId, RedisClientConnector.getRedis().hget(absolute, elementId))
                .setColumns(columns).setSchema(schema).setTable(table).toString();
        RedisClientConnector.getRedis().hset(absolute, elementId, value);
        return "success";
    }
}
