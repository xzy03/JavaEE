package cn.edu.zjut.utils;

import cn.edu.zjut.entity.dto.UserTokenInfoDto;
import cn.edu.zjut.exception.apiException.BusiException;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

/*
 * 用户信息工具类
 * 基于ThreadLocal实现的用户信息存储工具，用于在当前线程中存储和获取用户登录信息。
 * 主要用于在业务处理过程中获取当前登录用户的信息，避免频繁传递用户参数。
 * 注意：使用完毕后需要及时调用removeCurrentUser()方法清理ThreadLocal中的数据，防止内存泄漏。
 */
@Component
public class UserInfoUtils {
    private static final ThreadLocal<UserTokenInfoDto> CURRENT_USER_THREAD_LOCAL = new ThreadLocal<>();

    private UserInfoUtils() {
    }
    public static void setCurrentUser(UserTokenInfoDto user) {
        if (user == null) {
            throw new BusiException("用户信息不能为空");
        }
        CURRENT_USER_THREAD_LOCAL.set(user);
    }
    public static UserTokenInfoDto getCurrentUser() {
        UserTokenInfoDto user = CURRENT_USER_THREAD_LOCAL.get();
        if (user == null || user.getUserId() == null) {
            throw new BusiException("用户未登录或token无效");
        }
        return user;
    }
    public static void removeCurrentUser() {
        CURRENT_USER_THREAD_LOCAL.remove();
    }
    @PreDestroy
    public void destroy() {
        // Spring 容器销毁时清理 ThreadLocal
        CURRENT_USER_THREAD_LOCAL.remove();
    }
}
