package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.entity.Package;
import com.oocl.packagebooking.repository.PackageRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        initP1 = packageRepository.save(new Package("1234566789", 0, "13131313131", new Date(), 5.5));
        initP2 = packageRepository.save(new Package("1234566789", 0, "13131313131", new Date(), 5.5));
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
        Assertions.assertEquals(initP1.getId().intValue(), result.getJSONObject(0).getInt("id"));

    }
}
