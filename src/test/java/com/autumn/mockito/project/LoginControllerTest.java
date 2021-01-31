package com.autumn.mockito.project;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.notNullValue;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/22
 * @time 10:44
 * @description 测试Controller: 结论: 不成功
 * 参考地址: https://spring.io/guides/gs/testing-web/
 *
 */
// @SpringBootTest
// @RunWith(SpringRunner.class)
// @WebMvcTest(LoginController.class)
// @AutoConfigureMockMvc
public class LoginControllerTest {
    //
    // @Autowired
    // private LoginController controller;
    //
    // @Test
    // // 使用@DirtiesContext可以清空缓存,让程序重新加载
    // @DirtiesContext
    // public void testContextLoad() {
    //     Assert.assertThat(controller, notNullValue());
    // }

    // @Autowired
    // private MockMvc mockMvc;

    @Test
    public void name() throws Exception {
        LoginController controller = new LoginController();
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/web/login"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("默默")));
    }

    //
    // @Before
    // public void setUp() throws Exception {
    //     mvc = MockMvcBuilders.webAppContextSetup(context).build();
    //     // LoginController apiController = new LoginController();
    //     // mvc = MockMvcBuilders.standaloneSetup(apiController).build();
    // }
    //
    // @Test
    // public void login() throws Exception {
    //     mvc.perform(MockMvcRequestBuilders.get("/web/login")
    //             .contentType(MediaType.APPLICATION_JSON_UTF8)
    //             .param("name", "丁丁")
    //             .accept(MediaType.APPLICATION_JSON))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andDo(MockMvcResultHandlers.print())
    //             .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("丁丁")));
    // }
}