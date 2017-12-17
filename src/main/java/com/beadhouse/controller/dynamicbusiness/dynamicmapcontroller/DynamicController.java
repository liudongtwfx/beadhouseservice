package main.java.com.beadhouse.controller.dynamicbusiness.dynamicmapcontroller;


import main.java.com.beadhouse.DAO.Admin.HtmlViewRepository;
import main.java.com.beadhouse.System.CommonFinalVariable;
import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.dynamic.dataencapsulation.PaginationAreaData;
import main.java.com.beadhouse.dynamic.dataencapsulation.StructAnalysis;
import main.java.com.beadhouse.dynamic.dataencapsulation.StructAreaData;
import main.java.com.beadhouse.dynamic.dataencapsulation.TableAreaData;
import main.java.com.beadhouse.dynamic.html.areatemplate.AreaType;
import main.java.com.beadhouse.model.admin.HtmlView;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.StreamHandler;

@Controller
public class DynamicController {
    @Inject
    HtmlViewRepository viewRepository;

    @RequestMapping(value = "/dynamic/{url}")
    public ModelAndView viewPath(@PathVariable("url") String url,
                                 HttpServletRequest request,
                                 Model model) {
        LogType.DEBUGINFO.getLOGGER().debug(request.getRequestURI());
        HtmlView view = viewRepository.findByUrl(url);
        if (view == null) {
            return new ModelAndView("redirect:/not_found");
        }
        LogType.DEBUGINFO.getLOGGER().debug(url);
        LogType.DEBUGINFO.getLOGGER().debug("test/" + view.getFilepath());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test/" + view.getFilepath());
        modelAndView.addObject("url", url);
        checkFillDatas(modelAndView, view.getFilepath(), request);
        return modelAndView;
    }

    private void checkFillDatas(ModelAndView modelAndView, String filepath, HttpServletRequest request) {
        String absolute = CommonFinalVariable.ABSOLUTE_FILE_PATH + filepath + ".html";
        List<StructAreaData> structAnalyses = StructAnalysis.getStructAreaDatas(absolute);
        for (StructAreaData data : structAnalyses) {
            Map<String, String> params = new HashMap<>();
            fillinPaginationAndQueryData(request, modelAndView, params);
            if (data.getAreaType() == AreaType.FORM) {
                data.fillInDatas();
                modelAndView.addObject("formareadata", data);
            }
            if (data.getAreaType() == AreaType.TABLE) {
                ((TableAreaData) data).setParamsMap(params);
                data.fillInDatas();
                modelAndView.addObject("tableareadata", ((TableAreaData) data).getSqlResultData());
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void fillinPaginationAndQueryData(HttpServletRequest request,
                                              ModelAndView modelAndView,
                                              Map<String, String> params) {
        String page = request.getParameter("page");
        String pagesize = request.getParameter("pagesize");
        if (page != null && pagesize != null && page.length() != 0 && pagesize.length() != 0) {
            modelAndView.addObject("currpage", page);
            modelAndView.addObject("pagesize", pagesize);
            modelAndView.addObject("mainurl", request.getRequestURL());
            modelAndView.addObject("pager",
                    new PaginationAreaData(10, Integer.valueOf(page), 5));
        }
        request.getParameterMap().forEach((k, v) -> params.putIfAbsent((String) k, ((String[]) v)[0]));
    }
}