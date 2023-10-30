package az.bank.dto.converter;

import az.bank.dto.CustomerAccountDto;
import az.bank.model.Account;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerAccountDtoConverter {

    private final TransactionDtoConverter transactionDtoConverter;

    public CustomerAccountDtoConverter(TransactionDtoConverter transactionDtoConverter) {
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public CustomerAccountDto convert(Account account){
        return CustomerAccountDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .createDate(account.getCreateDate())
                .transactions(account.getTransactions().stream()
                        .map(transactionDtoConverter::convert)
                        .collect(Collectors.toList()))
                .build();
    }
}
