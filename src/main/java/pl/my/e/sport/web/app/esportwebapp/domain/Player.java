package pl.my.e.sport.web.app.esportwebapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
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