package pl.my.e.sport.web.app.esportwebapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@Entity
@Table(name = "account_type")
public class AccountType {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true)
    @NotNull
    private Integer id;

    @NotNull
    private String role;

}

