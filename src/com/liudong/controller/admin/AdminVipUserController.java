package com.liudong.controller.admin;

import com.liudong.DAO.User.VipUser.VipUserRepository;
import com.liudong.model.User.VipUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 * Created by liudong on 2017/5/27.
 */

@Controller
@RequestMapping(value = "/admin/vipuser")
public class AdminVipUserController {
    @Inject
    VipUserRepository vipUserRepository;

    @RequestMapping(value = "list")
    @ResponseBody
    public Page<VipUser> getVipUserList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "size", defaultValue = "15") Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);
        return this.vipUserRepository.findAll(pageable);
    }
}
