package pl.my.e.sport.web.app.esportwebapp.services;

import pl.my.e.sport.web.app.esportwebapp.domain.Account;

public interface AccountService {

    Account checkCredentials(Account account);

    Account create(Account account);

    Account findByEmail(String email);
}
