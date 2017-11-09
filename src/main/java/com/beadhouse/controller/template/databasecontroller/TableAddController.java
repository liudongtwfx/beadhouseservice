package main.java.com.beadhouse.controller.template.databasecontroller;


import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.dynamic.database.databasemetadata.CreateTableImpl;
import main.java.com.beadhouse.dynamic.database.databasemetadata.Table;
import org.hibernate.validator.constraints.pl.REGON;
import org.hswebframework.ezorm.rdb.meta.RDBColumnMetaData;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/database/table")
public class TableAddController {
    @RequestMapping(value = "add")
    public String addTablePage(Table table) {
        LogType.DEBUGINFO.getLOGGER().debug(table.toString());
        return "addtable";
    }

    @ModelAttribute("ColumnTypes")
    public List<String> getColumnTypes() {
        List<String> attrbutes = new ArrayList<>();
        for (JDBCType type : JDBCType.values()) {
            attrbutes.add(type.getName());
        }
        return attrbutes;
    }

    @RequestMapping(value = "add", params = {"addColumnRow"})
    public String addColumnRow(Table table, BindingResult result) {
        LogType.DEBUGINFO.getLOGGER().debug(table.toString());
        table.getColumns().add(new RDBColumnMetaData());
        return "addtable";
    }

    @RequestMapping(value = "add", params = {"addTable"})
    public String addNewTable(Table table, ModelMap modelMap) {
        String message = new CreateTableImpl().createTableByTable(table);
        String info = "failure";
        if (message.equals("success")) {
            message = "已成功添加数据表";
            info = "success";
        }
        modelMap.addAttribute("callbackinfo", info);
        modelMap.addAttribute("callback", message);
        return "addtable";
    }

    @RequestMapping(value = "add", params = {"removeColumn"})
    public String removeColumn(Table table, HttpServletRequest request) {
        int index = Integer.valueOf(request.getParameter("removeColumn"));
        table.getColumns().remove(index);
        return "addtable";
    }
}
