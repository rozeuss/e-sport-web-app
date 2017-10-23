package pl.my.e.sport.web.app.esportwebapp.domain.dto;

public class MatchDto {

    private long id;
    private long tournamentId;
    private int phase;
    private static MatchDto ourInstance = new MatchDto();

    public static MatchDto getInstance() {
        return ourInstance;
    }

    private MatchDto(){};

    public MatchDto(long tournamentId, int phase) {
        this.tournamentId = tournamentId;
        this.phase = phase;
    }
}
