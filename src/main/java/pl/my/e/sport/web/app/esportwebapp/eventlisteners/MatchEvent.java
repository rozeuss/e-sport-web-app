package pl.my.e.sport.web.app.esportwebapp.eventlisteners;

import org.springframework.context.ApplicationEvent;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;


public class MatchEvent extends ApplicationEvent {

    public static final int FINAL_PHASE = 1;
    public static final int PLAYOFF_PHASE = 0;
    private final boolean isFinalised;
    private Match match;

    public MatchEvent(Object source, Match match) {
        super(source);
        this.match = match;
        this.isFinalised = checkIfMatchIsFinalized();
    }

    public Match getMatch() {
        return match;
    }

    public boolean isFinalised() {
        return isFinalised;
    }

    private boolean checkIfMatchIsFinalized() {
        return match.getScoreAway() != null && match.getScoreHome() != null
                && match.getTeamHome() != null && match.getTeamAway() != null
                && match.getPhase() != FINAL_PHASE && match.getPhase() != PLAYOFF_PHASE;
    }
}
