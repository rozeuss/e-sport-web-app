package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Tournament;
import pl.my.e.sport.web.app.esportwebapp.repositories.TournamentRepository;
import pl.my.e.sport.web.app.esportwebapp.services.TournamentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentServiceImpl implements TournamentService {

    private TournamentRepository tournamentRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public List<Tournament> listAll() {
        List<Tournament> tournaments = new ArrayList<>();
        tournamentRepository.findAll().forEach(tournaments::add);
        return tournaments;
    }

    @Override
    public Tournament findById(Long id) {
        return tournamentRepository.findOne(id);
    }

    @Override
    public Tournament create(Tournament tournament) {
        if (!tournament.getEndDate().isAfter(tournament.getStartDate())) {
            return null;
        } else {
            return tournamentRepository.save(tournament);
        }
    }
}
