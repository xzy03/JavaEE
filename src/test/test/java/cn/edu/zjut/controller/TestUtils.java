package cn.edu.zjut.controller;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class TestUtils {
	public static String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InBob25lTnVtIjoiMTczNjQ1NjM2NTkiLCJ1c2VySWQiOiJBMDAwMDAwMDAifSwic3ViIjoiQTAwMDAwMDAwIiwiZXhwIjoxNzQ5Mjk3MDU0LCJqdGkiOiIxMzllODEyOWM4YzU0MmI2YTIzOTUzMDNlZDkwOTJiYSJ9.80HPdqFXK_6yk41Csn2ZwWubcr1QjoCT90MIX_Vp63k";

    public static MockHttpServletRequestBuilder addToken(MockHttpServletRequestBuilder request) {
        return request.header("Authorization", "Bearer " + TOKEN);
    }
}
