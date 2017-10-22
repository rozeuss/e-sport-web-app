package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Match;
import pl.my.e.sport.web.app.esportwebapp.services.MatchService;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    @Override
    public List<Match> listAll() {
        return null;
    }

    @Override
    public Match findById(Long id) {
        return null;
    }

    @Override
    public Match save(Match match) {
        return null;
    }

    @Override
    public Match findPlayoff() {
        return null;
    }

    @Override
    public List<Match> createMatchesForTournament(long id, int numberOfParticipants) {
        return null;
    }

    @Override
    public Match create(Match match) {
        return null;
    }
}
