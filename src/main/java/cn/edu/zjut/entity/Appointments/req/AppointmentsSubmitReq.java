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
public class AppointmentsSubmitReq {

    @Schema(description = "房源ID")
    @NotBlank
    private String houseId;

    @Schema(description = "预约时间")
    private Date appointmentDate;
}
