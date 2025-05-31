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
public class landlordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegister() throws Exception {
    	String ts = String.valueOf(System.currentTimeMillis());
    	ts = ts.substring(0,11);
    	String body = "{\"laccount\":\"" + ts.substring(0, 10) + "\",\"lpassword\":\"123456\",\"lphoneNumber\":\"" + ts + "\",\"lemail\":\"" + ts + "@163.com\"}";
        mockMvc.perform(post("/landlords/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }
    
    @Test
    public void testLogin() throws Exception {
    	String body = "{\"lphoneNumber\":\"18868179603\",\"lpassword\":\"123456\"}";
        mockMvc.perform(post("/landlords/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.token").isString());
    }

    @Test
    public void testGetLandlordList() throws Exception {
        String body = "{}";
        
        mockMvc.perform(TestUtils.addToken(post("/landlords/getLandlordList"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.landlordList").isArray());
    }
}
