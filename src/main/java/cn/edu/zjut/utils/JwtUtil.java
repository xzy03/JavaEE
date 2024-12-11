package cn.edu.zjut.utils;

import cn.edu.zjut.entity.admins.dto.AdminsTokenInfoDto;
import cn.edu.zjut.exception.apiException.BusiException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: Jwt工具类
 * @createDate: 2024/12/11
 * @author: 项郑毅
 * */
@Slf4j
public class JwtUtil {
    private static final String JWT_SECRET = "xzyhllbcyk#javaee$2024@secure*token&key%production!environment";
    private static final long EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000;

    /**
     * 生成JWT token
     * @param adminId 管理员用户ID
     * @param adUsername 管理员用户名
     * @return JWT token字符串
     */
    public static String generateToken(String adminId, String adUsername) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("adminId", adminId);
        claims.put("adUsername", adUsername);

        return JWT.create()
                .withClaim("user", claims)
                .withSubject(adminId)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withJWTId(UUID.randomUUID().toString().replace("-", ""))
                .sign(Algorithm.HMAC256(JWT_SECRET));
    }

    /**
     * 从JWT token中解析用户信息
     * @param token JWT token字符串
     * @return AdminsTokenInfoDto 包含用户ID和用户名的对象
     */
    public static AdminsTokenInfoDto parseToken(String token) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                    .build()
                    .verify(token);

            Map<String, Object> userClaims = jwt.getClaim("user").asMap();

            AdminsTokenInfoDto adminInfo = new AdminsTokenInfoDto();
            adminInfo.setAdminId((String) userClaims.get("adminId"));
            adminInfo.setAdUsername((String) userClaims.get("adUsername"));

            return adminInfo;
        } catch (TokenExpiredException e) {
            throw new BusiException("Token已过期");
        } catch (Exception e) {
            log.error("Token解析失败: {}", e.getMessage());
            throw new BusiException("Token无效");
        }
    }
}
