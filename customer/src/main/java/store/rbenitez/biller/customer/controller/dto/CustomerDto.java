package store.rbenitez.biller.customer.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {
    private Integer id;

    @NotEmpty(message = "documentNumber value is required")
    @Pattern(regexp = "^[0-9]{8}$", message = "debe coincidir con 8 digitos numericos")
    private String documentNumber;

    @NotEmpty(message = "documentType value is required")
    @Size(min = 2, max = 3)
    private String documentType;

    @NotEmpty(message = "name value is required")
    @Size(max = 100)
    private String name;

    @NotEmpty(message = "lastName value is required")
    @Size(max = 100)
    private String lastName;

    @NotEmpty(message = "email value is required")
    private String email;

    @NotEmpty(message = "phoneNumber value is required")
    @Pattern(regexp = "^[0-9]{9}$", message = "debe coincidir con 9 digitos numericos")
    private String phoneNumber;

    @Size(max = 100)
    private String address;
}
