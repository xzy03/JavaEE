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
public class ContractsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    
    @Test
    public void testGetContractsDetail() throws Exception {
        String body = "{\"contractsId\":\"C00000000\"}";
        
        mockMvc.perform(TestUtils.addToken(post("/contacts/getContractsDetail"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.lname").isNotEmpty());
    }
    
    @Test
    public void testViewTenantContracts() throws Exception {
        String body = "{}";
        
        mockMvc.perform(TestUtils.addToken(post("/contacts/viewTenantContracts"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.contractsList").isArray());
    }
    
    @Test
    public void testViewLandlordContracts() throws Exception {
        String body = "{}";
        
        mockMvc.perform(TestUtils.addToken(post("/contacts/viewLandlordContracts"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.contractsList").isArray());
    }
}
