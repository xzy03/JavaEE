package cn.edu.zjut.entity.Appointments.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentViewLandlordResp {
    @Schema(description = "预约id")
    private String appointmentId;

    @Schema(description = "房源名称")
    private String hTitle;

    @Schema(description = "租客姓名")
    private String tName;

    @Schema(description = "预约日期")
    private Date appointmentDate;

    @Schema(description = "预约状态")
    private String status;

}
