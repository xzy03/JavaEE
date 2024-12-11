package cn.edu.zjut.entity.admins.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    管理员token信息传输对象
    @author 项郑毅
    @create 2024/12/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminsTokenInfoDto {
    private String adminId;
    private String adUsername;
}
