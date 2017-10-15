package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Tournament;
import pl.my.e.sport.web.app.esportwebapp.services.TournamentService;

import java.util.List;

@Service
public class TournamentServiceImpl implements TournamentService {
    @Override
    public List<Tournament> listAll() {
        return null;
    }

    @Override
    public Tournament findById(Long id) {
        return null;
    }

    @Override
    public Tournament save(Tournament tournament) {
        return null;
    }
}
