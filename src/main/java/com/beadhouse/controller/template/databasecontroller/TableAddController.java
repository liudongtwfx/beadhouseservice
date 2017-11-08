package main.java.com.beadhouse.controller.template.databasecontroller;


import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.dynamic.database.databasemetadata.CreateTableImpl;
import main.java.com.beadhouse.dynamic.database.databasemetadata.Table;
import org.hswebframework.ezorm.rdb.meta.RDBColumnMetaData;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/database/table")
public class TableAddController {
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addTablePage(final CreateTableImpl createTable, Model model) {
        model.addAttribute("first_title", "数据库管理中心");
        model.addAttribute("second_title", "新增数据表");
        model.addAttribute("actionurl", "/admin/database/table/add");
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
    public String addColumnRow(final CreateTableImpl createTable, Model model) {
        LogType.DEBUGINFO.getLOGGER().debug(createTable.toString());
        LogType.DEBUGINFO.getLOGGER().debug(createTable.getTable().getColumns().size());
        createTable.getTable().getColumns().add(new RDBColumnMetaData());
        return "addtable";
    }
}
