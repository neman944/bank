package az.bank.dto.converter;

import az.bank.dto.TransactionDto;
import az.bank.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {

    public TransactionDto convert(Transaction transaction){
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .transactionDate(transaction.getTransactionDate())
                .transactionType(transaction.getTransactionType())
                .build();
    }
}
