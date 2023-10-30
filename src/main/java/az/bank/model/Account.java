package az.bank.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal balance;
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(getId(), account.getId()) && Objects.equals(getBalance(), account.getBalance()) && Objects.equals(getCreateDate(), account.getCreateDate()) && Objects.equals(getCustomer(), account.getCustomer()) && Objects.equals(getTransactions(), account.getTransactions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBalance(), getCreateDate(), getCustomer(), getTransactions());
    }
}
