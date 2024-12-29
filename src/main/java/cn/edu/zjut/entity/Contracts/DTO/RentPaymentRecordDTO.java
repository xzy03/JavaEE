package cn.edu.zjut.entity.Contracts.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

//用来保存房东对房租的查看信息
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentPaymentRecordDTO {
    private String tenantName;
    private BigDecimal paymentAmount;
    private Date paymentDate;
    private String transactionStatus; // 交易状态，如"已支付"等
    private String houseName;

}
