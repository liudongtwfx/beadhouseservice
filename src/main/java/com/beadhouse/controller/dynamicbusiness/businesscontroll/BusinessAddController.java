package main.java.com.beadhouse.controller.dynamicbusiness.businesscontroll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin/business/add")
public class BusinessAddController {
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String addbusinessPaget() {
        return "business/businessbaseinfo";
    }

    @RequestMapping(value = "/", params = {"submit"}, method = RequestMethod.POST)
    public String addNewBusiness(HttpServletRequest request) {

        return "redirect:/admin/views/edit";
    }
}
