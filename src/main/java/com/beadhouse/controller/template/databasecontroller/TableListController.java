package main.java.com.beadhouse.controller.template.databasecontroller;

import main.java.com.beadhouse.dynamic.database.operate.DatabaseStructureQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin/database/table")
public class TableListController {
    private static final String[] dataBase = {"demo", "test"};

    @RequestMapping(value = "list")
    public String getListPage(Model model) {
        model.addAttribute("callback", "");
        model.addAttribute("callbackinfo", "");
        model.addAttribute("schemaselected", "");
        return "tables";
    }

    @ModelAttribute(value = "databases")
    public String[] getDataBase() {
        return dataBase;
    }

    @RequestMapping(value = "list", params = {"schema"})
    public String getSchema(HttpServletRequest request, Model model) {
        String schema = request.getParameter("schema");
        model.addAttribute("tablelist", new DatabaseStructureQuery(schema).getAllTablesName());
        return "table";
    }
}
