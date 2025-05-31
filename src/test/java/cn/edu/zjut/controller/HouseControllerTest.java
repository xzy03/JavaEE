package cn.edu.zjut.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetHouseList() throws Exception {
    	String body = "{}";
        mockMvc.perform(TestUtils.addToken(post("/house/getHouseList"))
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.houseList").isArray());
    }

    @Test
    public void testGetHouseDetail() throws Exception {
        String body = "{ \"houseId\":\"H00000001\" }";
        
        mockMvc.perform(TestUtils.addToken(post("/house/getHouseDetail"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.lname").isNotEmpty());
    }
}
