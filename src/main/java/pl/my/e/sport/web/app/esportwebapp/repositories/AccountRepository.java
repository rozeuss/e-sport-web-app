package pl.my.e.sport.web.app.esportwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.my.e.sport.web.app.esportwebapp.domain.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findAccountByEmailAndPassword(String email, String password);

    Optional<Account> findByEmail(String email);

}
