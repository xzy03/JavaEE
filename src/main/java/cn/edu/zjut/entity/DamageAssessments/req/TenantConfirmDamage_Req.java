package cn.edu.zjut.entity.DamageAssessments.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TenantConfirmDamage_Req {
    private String damageId;
    private String tenantId;
    private String daStatus;

}
