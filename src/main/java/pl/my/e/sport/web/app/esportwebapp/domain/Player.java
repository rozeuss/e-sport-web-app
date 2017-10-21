package pl.my.e.sport.web.app.esportwebapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "player_seq", allocationSize = 1)
    private long id;
    @NotNull
    private String playerName;
    private String firstName;
    private String lastName;
    @ManyToOne
    private Team team;

    public Player(String playerName, String firstName, String lastName, Team team) {
        this.playerName = playerName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
    }
}