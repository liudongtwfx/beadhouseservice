package main.java.com.beadhouse.controller.dynamicbusiness.databasecontroller;


import main.java.com.beadhouse.System.CommonFinalVariable;
import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.dynamic.database.operate.CreateTableImpl;
import main.java.com.beadhouse.dynamic.database.databasemetadata.Table;
import org.hswebframework.ezorm.rdb.meta.RDBColumnMetaData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/database/table")
public class TableAddController {
    @RequestMapping(value = {"add", "addtable"})
    public String addTablePage(Table table) {
        LogType.DEBUGINFO.getLOGGER().debug(table.toString());
        return "addtable";
    }

    @ModelAttribute("ColumnTypes")
    public List<String> getColumnTypes() {
        return CommonFinalVariable.getJdbcTypes();
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
