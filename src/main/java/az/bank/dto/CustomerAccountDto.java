package az.bank.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Data
@Builder
public class CustomerAccountDto {
    private Long id;
    private BigDecimal balance;
    private LocalDateTime createDate;
    private List<TransactionDto> transactions;
}
