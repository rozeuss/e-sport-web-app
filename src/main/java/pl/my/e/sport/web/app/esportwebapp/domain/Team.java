package pl.my.e.sport.web.app.esportwebapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "team_seq", allocationSize = 1)
    private long id;

    @NotNull
    private String name;

    @Email
    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String country;

    @OneToOne
    private Statistics statistics;

    public Team(String name, String email, String password, String country, Statistics statistics) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.statistics = statistics;
    }
}
