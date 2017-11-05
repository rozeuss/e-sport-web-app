package pl.my.e.sport.web.app.esportwebapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "match")
public class Match {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "match_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    private Match nextMatch;
    //    @NotNull @Todo i chyba raczej LocalDateTime!
    private LocalDate startDate;

    @ManyToOne
    private Team teamAway;

    @ManyToOne
    private Team teamHome;

    private long scoreAway;

    private long scoreHome;

    @NotNull
    @ManyToOne
    private Tournament tournament;

    //kiedy nextMatch == null && phase == null to jest to playoff
    private int phase;

    public Match(LocalDate startDate, Team teamAway, Team teamHome, long scoreAway,
                 long scoreHome, Tournament tournament) {
        this.startDate = startDate;
        this.teamAway = teamAway;
        this.teamHome = teamHome;
        this.scoreAway = scoreAway;
        this.scoreHome = scoreHome;
        this.tournament = tournament;
    }
}
