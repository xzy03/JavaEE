package cn.edu.zjut.controller;

import cn.edu.zjut.annotation.PassAuthentication;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 页面控制器
 * 用于处理网页请求和视图渲染
 * @Author bc
 * @Date 2024/12/25
 */
@Slf4j
@Controller
@Tag(name = "前端管理", description = "网页相关的 API")
public class WebController {
    @PassAuthentication
    @GetMapping("/index")
    public String index() {
        System.out.println("进入index界面");
        return "index"; // 返回视图
    }

    @PassAuthentication
    @GetMapping("/login")
    public String login() {
        System.out.println("进入login界面");
        return "login"; // 返回视图
    }

    @PassAuthentication
    @GetMapping("/register")
    public String register() {
        System.out.println("进入register界面");
        return "register"; // 返回视图
    }

    @PostMapping("/userDashboard")
    public String userDashboard() {
        System.out.println("进入userDashboard界面");
        return "userDashboard"; // 返回视图
    }

}
