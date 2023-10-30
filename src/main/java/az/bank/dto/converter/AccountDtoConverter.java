package az.bank.dto.converter;

import az.bank.dto.AccountCustomerDto;
import az.bank.dto.AccountDto;
import az.bank.model.Account;
import az.bank.model.Customer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {

    private final TransactionDtoConverter transactionDtoConverter;

    public AccountDtoConverter(TransactionDtoConverter transactionDtoConverter) {
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public AccountDto convert(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .createDate(account.getCreateDate())
                .transactions(account.getTransactions().stream()
                        .map(transactionDtoConverter::convert)
                        .collect(Collectors.toList()))
                .customer(convert(account.getCustomer()))
                .build();
    }
    private AccountCustomerDto convert(Customer customer){
        return AccountCustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .build();
    }

}
