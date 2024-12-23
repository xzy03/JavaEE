package cn.edu.zjut.entity.TenantProfile.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantIdcardReq {
    @Schema(description = "租户身份证号")
    private String tCardNumber;

    @Schema(description = "租户姓名")
    private String tName;

    @Schema(description = "租户身份证正面照")
    private MultipartFile tCardImageFront;

    @Schema(description = "租户身份证背面照")
    private MultipartFile tCardImageBack;
}
