package cn.edu.zjut.entity.DamageAssessments.resp;

import cn.edu.zjut.entity.DamageAssessments.DTO.FindDamage_assessmentsDTO;
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
public class FindDamage_assessmentsResp {
    @Schema(description = "查找到的所有的房屋定损信息")
    List<FindDamage_assessmentsDTO> findDamage_assessmentsDTOList;
}
