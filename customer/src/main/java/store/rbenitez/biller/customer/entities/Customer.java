package store.rbenitez.biller.customer.entities;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import store.rbenitez.biller.customer.controller.dto.DocumentEnum;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@Table("customers")
public class Customer {
    @Id
    private Integer id;
    private String documentType;
    private String documentNumber;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;
}
