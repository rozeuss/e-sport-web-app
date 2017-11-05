package pl.my.e.sport.web.app.esportwebapp.services;

import pl.my.e.sport.web.app.esportwebapp.domain.Tournament;

import java.util.List;

public interface TournamentService {

    List<Tournament> listAll();

    Tournament findById(Long id);

    Tournament create(Tournament tournament);

    List<Tournament> findAllByOrganizer(Long accountId);

}
