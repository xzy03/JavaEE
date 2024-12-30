package cn.edu.zjut.filter;


import cn.edu.zjut.annotation.PassAuthentication;
import cn.edu.zjut.utils.UserInfoUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.utils.JwtUtil;

import java.lang.reflect.Method;

/*
    用户信息拦截器
    @author 项郑毅
    @create 2024/12/15
 */
@Component
@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {

    /**
     * 处理
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理程序
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        log.info("UserInfoInterceptor preHandle 进入拦截器");
        // 如果不是HandlerMethod类型，直接放行（适用于静态资源等）
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        else{
            HandlerMethod method = (HandlerMethod) handler;

            // 如果方法或类上标记了 @PassAuthentication 注解，则放行
            if (method.hasMethodAnnotation(PassAuthentication.class)) {
                return true;
            }
        }

        // 放行knife4j相关URL
        String requestUrl = request.getRequestURI();
        if (requestUrl.contains("/doc.html") ||
                requestUrl.contains("/swagger-resources") ||
                requestUrl.contains("/v3/api-docs")) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 创建用户信息对象
        UserTokenInfoDto user;
        // 检查是否有 PassAuthentication 注释
        if (!method.isAnnotationPresent(PassAuthentication.class)) {
            log.info("当前方法未添加PassAuthentication注解，进入拦截器");
            // 没有PassAuthentication注解时，进行token解析
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
            user = JwtUtil.parseToken(token);
        } else {
            user = new UserTokenInfoDto();
        }
        UserInfoUtils.setCurrentUser(user);
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserInfoUtils.removeCurrentUser();
        MDC.clear();
    }

}
