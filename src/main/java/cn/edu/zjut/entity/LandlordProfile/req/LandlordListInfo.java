package cn.edu.zjut.entity.LandlordProfile.req;

import cn.edu.zjut.entity.LandlordProfile.LandlordProfile;
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
public class LandlordListInfo {
    @Schema(description = "房东列表")
    List<LandlordProfile> landlordList;
}
