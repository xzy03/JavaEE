package cn.edu.zjut.controller;

import cn.edu.zjut.annotation.PassAuthentication;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

}
