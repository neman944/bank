package az.bank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountCustomerDto {
    private Long id;
    private String name;
    private String surname;
}
