package com.liudong.test;

import com.liudong.controller.user.VipUserController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by liudong on 2016/12/21.
 */
public class VipUserControllerTest {
    @Test
    public void homepagetest() throws Exception {
        VipUserController controller = new VipUserController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        //mockMvc.perform(get("/user")).andExpect(view().name("index"));
    }
}