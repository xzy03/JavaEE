package cn.edu.zjut.utils;

import cn.edu.zjut.entity.dto.UserTokenInfoDto;
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
     * @param userId 用户ID
     * @param phoneNum 用户手机号
     * @return JWT token字符串
     */
    public static String generateToken(String userId, String phoneNum) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("phoneNum", phoneNum);

        return JWT.create()
                .withClaim("user", claims)
                .withSubject(userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withJWTId(UUID.randomUUID().toString().replace("-", ""))
                .sign(Algorithm.HMAC256(JWT_SECRET));
    }

    /**
     * 从JWT token中解析用户信息
     * @param token JWT token字符串
     * @return UserTokenInfoDto 包含用户ID和用户名的对象
     */
    public static UserTokenInfoDto parseToken(String token) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                    .build()
                    .verify(token);

            Map<String, Object> userClaims = jwt.getClaim("user").asMap();

            UserTokenInfoDto userInfo = new UserTokenInfoDto();
            userInfo.setUserId((String) userClaims.get("userId"));
            userInfo.setPhoneNum((String) userClaims.get("phoneNum"));

            return userInfo;
        } catch (TokenExpiredException e) {
            throw new BusiException("Token已过期");
        } catch (Exception e) {
            log.error("Token解析失败: {}", e.getMessage());
            throw new BusiException("Token无效");
        }
    }
}
