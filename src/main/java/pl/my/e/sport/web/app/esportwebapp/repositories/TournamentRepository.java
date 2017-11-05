package pl.my.e.sport.web.app.esportwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.my.e.sport.web.app.esportwebapp.domain.Tournament;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament, Long> {

    Optional<Tournament> findByLocation(String location);

    Optional<Tournament> findByTitle(String title);

    Optional<Tournament> findByStartDate(LocalDate startDate);

    Optional<Tournament> findByEndDate(LocalDate endDate);

    List<Tournament> findAllByOrganizerId(Long accountId);

}