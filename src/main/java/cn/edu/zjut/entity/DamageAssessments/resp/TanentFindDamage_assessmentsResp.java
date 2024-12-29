package cn.edu.zjut.entity.DamageAssessments.resp;

import cn.edu.zjut.entity.DamageAssessments.DTO.FindDamage_assessmentsDTO;
import cn.edu.zjut.entity.DamageAssessments.DTO.TanentFindDamage_assessmentsDTO;
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
public class TanentFindDamage_assessmentsResp {

    //List<FindDamage_assessmentsDTO> findDamage_assessmentsDTOList

    @Schema(description = "查找到的所有的房屋定损信息")
    List<TanentFindDamage_assessmentsDTO> tanentFindDamage_assessmentsDTO;


}
