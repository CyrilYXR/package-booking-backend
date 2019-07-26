package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.entity.Package;
import com.oocl.packagebooking.repository.PackageRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PackageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PackageRepository packageRepository;

    private Package initP1;
    private Package initP2;

    @Before
    public void initDb(){
        initP1 = packageRepository.save(new Package(1234566789L, "张三",0, "13131313131", new Date(), 5.5));
        initP2 = packageRepository.save(new Package(1234566788L,"李四", 1, "13131313131", new Date(), 5.5));
    }

    @After
    public void cleanDb(){
        packageRepository.deleteAll();
    }

    @Test
    public void should_return_all_packages() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/packages")).andExpect(status().isOk())
                .andReturn();
        JSONArray result = new JSONArray(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(2, result.length());
    }

    @Test
    public void should_return_created_package_when_post() throws Exception {
        //given
        Package p = new Package(11111111L,"cyril", 0, "13131313131", new Date(), 5.5);
        //when
        MvcResult mvcResult = this.mockMvc.perform(post("/packages")
                .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(p)))
                .andExpect(status().isCreated())
                .andReturn();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());
        //then
        Assertions.assertEquals(p.getPhone(), result.getString("phone"));
    }

    @Test
    public void should_return_correct_packages_when_find_by_status() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/packages").param("status", "1")).andExpect(status().isOk())
                .andReturn();
        JSONArray result = new JSONArray(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(1, result.length());
        Assertions.assertEquals(initP2.getId().longValue(), result.getJSONObject(0).getInt("waybillNumber"));
        Assertions.assertEquals(1, result.getJSONObject(0).getInt("status"));
    }

    @Test
    public void should_return_correct_package_when_put() throws Exception {
        Package p = new Package(1234566789L,"cccc", 1, "13131313131", new Date(), 5.5);
        MvcResult mvcResult = this.mockMvc.perform(put("/packages/"+initP1.getId())
                .contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(p)))
                .andReturn();
        JSONObject result = new JSONObject(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(p.getStatus().intValue(), result.getInt("status"));
    }
}
