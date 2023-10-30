package az.bank.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class AccountDto {

    private Long id;
    private BigDecimal balance;
    private LocalDateTime createDate;
    private AccountCustomerDto customer;
    private List<TransactionDto> transactions;

}
