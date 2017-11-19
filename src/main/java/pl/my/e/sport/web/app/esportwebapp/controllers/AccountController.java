package pl.my.e.sport.web.app.esportwebapp.controllers;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.AccountDto;
import pl.my.e.sport.web.app.esportwebapp.domain.dto.AccountMapper;
import pl.my.e.sport.web.app.esportwebapp.services.AccountService;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Log4j
@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private AccountMapper accountMapper;

    @Autowired
    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @PostMapping("/create")
    public AccountDto create(@RequestParam String email, @RequestParam String password) {
        AccountDto accountDto = new AccountDto(email, password, 1); //TODO domyslny accountType
        return accountMapper.toDto(accountService.create(accountMapper.fromDto(accountDto)));
    }

    @GetMapping("/login")
    public AccountDto login(@RequestParam String email, @RequestParam String password) {
        AccountDto accountDto = new AccountDto(email, password);
        return accountMapper.toDto(accountService.checkCredentials(accountMapper.fromDto(accountDto)));
    }

    @GetMapping("/getByEmail")
    public AccountDto getByEmail(@RequestParam String email) {
        return accountMapper.toDto(accountService.findByEmail(email));
    }

}
