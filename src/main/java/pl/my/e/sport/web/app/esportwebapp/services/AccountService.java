package pl.my.e.sport.web.app.esportwebapp.services;

import pl.my.e.sport.web.app.esportwebapp.domain.Account;

public interface AccountService {

    boolean checkCredentials(Account account);

    Account create(Account account);
}
