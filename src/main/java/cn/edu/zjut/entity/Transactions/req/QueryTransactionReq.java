package cn.edu.zjut.entity.Transactions.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryTransactionReq {
    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "房东ID")
    private String landlordId;

    @Schema(description = "交易类型")
    private String tTransactionType;

    @Schema(description = "交易状态")
    private String tStatus;

    @Schema(description = "开始时间")

    @NotNull(message = "开始时间不能为空")
    private Date startTime;

    @Schema(description = "结束时间")
    @PastOrPresent(message = "结束时间不能晚于当前时间")
    @NotNull(message = "结束时间不能为空")
    private Date endTime;
}
