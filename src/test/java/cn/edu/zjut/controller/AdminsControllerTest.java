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
public class AdminsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegister() throws Exception {
    	String ts = String.valueOf(System.currentTimeMillis());
    	ts = ts.substring(0,11);
    	String body = "{\"adUsername\":\"" + ts.substring(0,10) + "\",\"adPasswordHash\":\"123456\",\"adEmail\":\"" + ts + "@qq.com\",\"adPhone\":\"" + ts + "\"}";
    	
        mockMvc.perform(post("/admins/register")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }
    
    @Test
    public void testLogin() throws Exception {
    	String body = "{\"adPhone\": \"17364563659\",\"adPasswordHash\": \"123456\"}";
    	
        mockMvc.perform(post("/admins/login")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.token").isString());
    }
}
