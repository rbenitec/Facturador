package store.rbenitez.biller.transaction.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDetailDto {
    private Integer transactionId;
    private String eanCode;
    private String sapCode;
    private String onuCode;
    private String description;
    private String size;
    private String unitOfMeasure;
    private String eFactUnitOfMeasure;
    private Double price;
    private Integer quantity;
    private Double baseAmount;
    private Double taxAmount;
    private Double discountAmount;
    private Double discountRate;
    private Double discountValue;
    private String discountPromotionCode;
    private String discountClassCode;
    private String discountCouponCode;
    private Double totalAmount;
    private LocalDateTime createdAt;
}
