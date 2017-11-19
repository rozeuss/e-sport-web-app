package pl.my.e.sport.web.app.esportwebapp.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchDto {

    private long id;
    private long nextMatchId;
    private String startDate;
    private TeamDto teamAway;
    private TeamDto teamHome;
    private Long scoreAway;
    private Long scoreHome;
    private long tournamentId;
    private int phase;

    public MatchDto(long id, long nextMatchId, String startDate, TeamDto teamAway, TeamDto teamHome, long scoreAway,
                    long scoreHome, long tournamentId, int phase) {
        this.id = id;
        this.nextMatchId = nextMatchId;
        this.startDate = startDate;
        this.teamAway = teamAway;
        this.teamHome = teamHome;
        this.scoreAway = scoreAway;
        this.scoreHome = scoreHome;
        this.tournamentId = tournamentId;
        this.phase = phase;
    }

    public MatchDto(Long scoreAway, Long scoreHome) {
        this.scoreAway = scoreAway;
        this.scoreHome = scoreHome;
    }

    public MatchDto(String startDate) {
        this.startDate = startDate;
    }
}
