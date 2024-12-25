package cn.edu.zjut.entity.TenantProfile.resq;

import cn.edu.zjut.entity.TenantProfile.TenantProfile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantListInfo {
    @Schema(description = "租户列表")
    List<TenantProfile> tenantList;
}
