package main.java.com.beadhouse.controller.dynamicbusiness.databasecontroller;

import main.java.com.beadhouse.System.CommonFinalVariable;
import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.dynamic.database.databasemetadata.Table;
import main.java.com.beadhouse.dynamic.database.operate.CreateTableImpl;
import main.java.com.beadhouse.dynamic.database.operate.DatabaseStructureQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/database/table")
public class TableListController {

    static class TableName {
        private static Map<String, List<String>> tableNameMap = new HashMap<>();

        static {
            update();
        }

        public static Map<String, List<String>> getTableNameMap() {
            return tableNameMap;
        }

        public static void update() {
            for (String schema : dataBase) {
                tableNameMap.put(schema, new DatabaseStructureQuery(schema).getAllTablesName());
            }
        }
    }

    private static final String[] dataBase = {"demo", "test"};

    @RequestMapping(value = "list")
    public String getListPage(@ModelAttribute(value = "table") Table table,
                              HttpServletRequest request,
                              Model model) {
        return "tables";
    }

    @ModelAttribute(value = "databases")
    public String[] getDataBase() {
        return dataBase;
    }

    @ModelAttribute(value = "schemaname")
    public List<String> getTables(String schema) {
        if (schema != null && !schema.isEmpty()) {
            return TableName.getTableNameMap().get(schema);
        }
        return new ArrayList<>();
    }

    @RequestMapping(value = "list", params = {"schema"}, method = RequestMethod.GET)
    public String getSchema(HttpServletRequest request, Model model) {
        String schema = request.getParameter("schema");
        model.addAttribute("schemaname", TableName.getTableNameMap().get(schema));
        return "tables::tablespart";
    }

    @RequestMapping(value = "list", params = {"tablename", "schema"})
    public String getTable(Table table, HttpServletRequest request, Model model) {
        String schema = request.getParameter("schema");
        String tablename = request.getParameter("tablename");
        try {
            CreateTableImpl.fillTableInfo(schema, tablename, table);
        } catch (SQLException e) {
            LogType.EXCETPION.getLOGGER().error(e);
            e.printStackTrace();
            model.addAttribute("error", e);
            model.addAttribute("callbackinfo", "failure");
        }
        LogType.DEBUGINFO.getLOGGER().debug(table);
        model.addAttribute("ColumnTypes", CommonFinalVariable.getJdbcTypes());
        return "addtable::#addTableForm";
    }
}