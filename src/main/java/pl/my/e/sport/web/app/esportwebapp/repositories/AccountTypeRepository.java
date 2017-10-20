package pl.my.e.sport.web.app.esportwebapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.my.e.sport.web.app.esportwebapp.domain.AccountType;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountType, Long> {
}
