package pl.my.e.sport.web.app.esportwebapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "team", uniqueConstraints = @UniqueConstraint(columnNames = "account"))
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "team_seq", allocationSize = 1)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    @OneToOne
    private Account account;

    public Team(String name, String country, Account account) {
        this.name = name;
        this.country = country;
        this.account = account;
    }
}
