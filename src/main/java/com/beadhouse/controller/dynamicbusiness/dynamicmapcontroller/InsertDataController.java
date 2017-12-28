package main.java.com.beadhouse.controller.dynamicbusiness.dynamicmapcontroller;


import main.java.com.beadhouse.dynamic.actionthandler.insertactionhandler.DatabaseInsertAction;
import main.java.com.beadhouse.dynamic.action.insertaction.InsertAction;
import main.java.com.beadhouse.dynamic.datawrapper.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class InsertDataController {
    @RequestMapping(value = "/dynamic/{url}/database/{schema}/{table}", method = RequestMethod.POST)
    public ModelAndView postDataToDatabase(@PathVariable("url") String url,
                                           @PathVariable(value = "schema") String schema,
                                           @PathVariable(value = "table") String table,
                                           HttpServletRequest request) {
        DatabaseInsertAction action = new DatabaseInsertAction(schema, table, request.getParameterMap());
        Data callback = action.handle(null);
        ModelAndView mv = new ModelAndView("forward:/dynamic/" + url);
        String color, info;
        if (callback.toString().equals("success")) {
            info = "已成功添加信息";
            color = "text-success";
        } else {
            info = callback.toString();
            color = "text-danger";
        }
        mv.addObject("callback", info);
        mv.addObject("textcolor", color);
        return mv;
    }
}
