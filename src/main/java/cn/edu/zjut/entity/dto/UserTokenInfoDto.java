package cn.edu.zjut.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    用户token信息传输对象
    @author 项郑毅
    @create 2024/12/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTokenInfoDto {
    private String userId;
    private String phoneNum;
}
