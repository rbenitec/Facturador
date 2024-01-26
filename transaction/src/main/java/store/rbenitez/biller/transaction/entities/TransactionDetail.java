package store.rbenitez.biller.transaction.entities;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(name = "transaction_details")
public class TransactionDetail {
    @Id
    private Integer id;
    private Integer transactionId;
    private String eanCode;
    private String sapCode;
    private String onuCode;
    private String description;

    @Column("[size]")
    private String size;

    private String unitOfMeasure;
    private String eFactUnitOfMeasure;
    private Double price;
    private Integer quantity;
    private Double baseAmount;
    private Double taxAmount;
    private Double discountAmount;
    private Double totalAmount;
    private Double discountRate;
    private Double discountValue;
    private String discountPromotionCode;
    private String discountClassCode;
    private String discountCouponCode;
    private LocalDateTime createdAt;
}
