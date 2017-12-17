package main.java.com.beadhouse.controller.dynamicbusiness.databasecontroller;


import main.java.com.beadhouse.dynamic.database.operate.DataBaseStaticMethods;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.standard.processor.AbstractStandardFragmentInsertionTagProcessor;

import java.util.List;

@Controller
public class TableParamsController {
    @RequestMapping(value = "/gettable/{schema}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getTables(@PathVariable("schema") String schema) {
        return DataBaseStaticMethods.getTables(schema);
    }

    @RequestMapping(value = "/getcolumns/{schema}/{table}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getColumns(@PathVariable("schema") String schema, @PathVariable("table") String table) {
        return DataBaseStaticMethods.getColumns(schema, table);
    }
}
