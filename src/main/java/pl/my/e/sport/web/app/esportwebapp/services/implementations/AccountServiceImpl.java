package pl.my.e.sport.web.app.esportwebapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.my.e.sport.web.app.esportwebapp.domain.Account;
import pl.my.e.sport.web.app.esportwebapp.repositories.AccountRepository;
import pl.my.e.sport.web.app.esportwebapp.services.AccountService;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account checkCredentials(Account account) {
        Optional<Account> accountByEmailAndPassword =
                accountRepository.findAccountByEmailAndPassword(account.getEmail(), account.getPassword());
        return accountByEmailAndPassword.orElse(null);
    }

    @Override
    public Account create(Account account) {
        Optional<Account> accountByEmailId = accountRepository.findByEmail(account.getEmail());
        if (accountByEmailId.isPresent()) {
            return null;
        }
        return accountRepository.save(account);
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElse(null);
    }
}
