package az.bank.dto;

import az.bank.model.Transaction;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {

    private Long id;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private Transaction.TransactionType transactionType;
}
