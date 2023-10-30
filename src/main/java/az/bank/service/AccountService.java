package az.bank.service;

import az.bank.dto.AccountDto;
import az.bank.dto.CreateAccountRequest;
import az.bank.dto.converter.AccountDtoConverter;
import az.bank.model.Account;
import az.bank.model.Customer;
import az.bank.model.Transaction;
import az.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter converter;
    private final Clock clock;

    public AccountService(AccountRepository accountRepository, CustomerService customerService, AccountDtoConverter converter, Clock clock) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.converter = converter;
        this.clock = clock;
    }

    public AccountDto createAccount(CreateAccountRequest request) {
        Customer customer = customerService.findCustomerById(request.getCustomerId());

        Account account = Account.builder()
                .customer(customer)
                .createDate(getLocalDateTimeNow())
                .balance(request.getInitialCredit())
                .build();
        if (request.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = Transaction.builder()
                    .amount(request.getInitialCredit())
                    .transactionDate(getLocalDateTimeNow())
                    .account(account)
                    .build();

            account.getTransactions().add(transaction);
        }
        return converter.convert(accountRepository.save(account));
    }

    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone());
    }
}
