package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Account;
import pl.my.e.sport.web.app.esportwebapp.domain.Tournament;
import pl.my.e.sport.web.app.esportwebapp.repositories.AccountRepository;
import pl.my.e.sport.web.app.esportwebapp.repositories.TournamentRepository;
import pl.my.e.sport.web.app.esportwebapp.services.TournamentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentServiceImpl implements TournamentService {

    private TournamentRepository tournamentRepository;
    private AccountRepository accountRepository;

    @Autowired
    public TournamentServiceImpl(TournamentRepository tournamentRepository, AccountRepository accountRepository) {
        this.tournamentRepository = tournamentRepository;
        this.accountRepository = accountRepository;
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
            //TODO ponizsze w celach testowych, pozniej pobierane z tokena
            Account one = accountRepository.findOne(1L);
            tournament.setOrganizer(one);
            return tournamentRepository.save(tournament);
        }
    }

    @Override
    public List<Tournament> findAllByOrganizer(Long accountId) {
        return tournamentRepository.findAllByOrganizerId(accountId);
    }
}
