package cn.edu.zjut.entity.Appointments.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 预约请求对象
 * @Author bc
 * @Date 2024/12/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentReq {

    @Schema(description = "预约ID（修改时需要）")
    private String appointmentId;

    @Schema(description = "房源ID")
    @NotBlank
    private String houseId;

    @Schema(description = "房东ID")
    @NotBlank
    private String landlordId;

    @Schema(description = "租客ID")
    @NotBlank
    private String tenantId;

    @Schema(description = "预约时间")
    @NotBlank
    private Date appointmentDate;

    @Schema(description = "预约状态")
    @NotBlank
    private String status;
}
