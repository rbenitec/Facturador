package store.rbenitez.biller.transaction.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {
    private String storeCode;
    private String vendorCode;
    private Double amount;
    private String paymentReceiptCode;
    private Integer customerId;
    private String discountCouponCode;
    private String paymentMethodCode;
    private Integer status;
    private String currencyCode;
    private String deviceSessionId;
    private Integer payId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double exchangeRate;
    private Double changeN;
    private Double changeF;
    private Double cashOutN;
    private Double cashOutF;
    private Double rounding;
    private String creditnoteReference;
    private String creditnote_reason_code;
    private CustomerDto customerDto;
    private List<TransactionDetailDto> transactionDetailDto;
}
