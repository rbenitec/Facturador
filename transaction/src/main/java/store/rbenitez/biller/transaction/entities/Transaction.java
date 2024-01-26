package store.rbenitez.biller.transaction.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transactions")
public class Transaction {
    @Id
    private Integer id;
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
}
