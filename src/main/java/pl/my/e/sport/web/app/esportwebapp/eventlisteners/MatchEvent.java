package pl.my.e.sport.web.app.esportwebapp.eventlisteners;

import org.springframework.context.ApplicationEvent;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;


public class MatchEvent extends ApplicationEvent {

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
                && match.getPhase() != 1 && match.getPhase() != 0;
    }
}
