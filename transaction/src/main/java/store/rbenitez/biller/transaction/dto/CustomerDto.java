package store.rbenitez.biller.transaction.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {
    private Integer id;
    private String name; // No puedes mayor 30 carcateres
    private String lastName; // No puedes mayor 30 carcateres
    private String documentNumber; //12345678
    private String documentType; //
    private String email; // Correo correcto : Regex[]
    private String address; // No sea mayor 50
    private LocalDateTime localDateTime;
    private String phoneNumber; // numerico no mayor 9 digitos
}
