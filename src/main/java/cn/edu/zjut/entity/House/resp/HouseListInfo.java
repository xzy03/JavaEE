package cn.edu.zjut.entity.House.resp;

import cn.edu.zjut.entity.House.House;
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
public class HouseListInfo {
    @Schema(description = "房源列表")
    List<House> houseList;
}
