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
public class TenantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegister() throws Exception {
    	String ts = String.valueOf(System.currentTimeMillis());
    	ts = ts.substring(0,11);
    	String body = "{\"taccount\":\"" + ts.substring(0, 10) + "\",\"tpassword\":\"123456\",\"tphoneNumber\":\"" + ts + "\",\"temail\":\"" + ts + "@163.com\"}";
        mockMvc.perform(post("/tenant/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));
    }
    
    @Test
    public void testLogin() throws Exception {
    	String body = "{\"phoneNum\":\"17590124683\",\"password\":\"123456\"}";
        mockMvc.perform(post("/tenant/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.token").isString());
    }

    @Test
    public void testGetTenantList() throws Exception {
    	String body = "{}";
        mockMvc.perform(TestUtils.addToken(post("/tenant/getTenantList"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.tenantList").isArray());
    }
}
