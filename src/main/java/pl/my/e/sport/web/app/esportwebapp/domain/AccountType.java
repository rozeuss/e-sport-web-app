package pl.my.e.sport.web.app.esportwebapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "account_type")
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "account_type_seq", allocationSize = 1)
    private long id;

    @NotNull
    private String role;

    public AccountType(String role) {
        this.role = role;
    }
}

