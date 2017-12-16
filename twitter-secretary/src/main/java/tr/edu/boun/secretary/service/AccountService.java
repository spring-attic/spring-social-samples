package tr.edu.boun.secretary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import tr.edu.boun.secretary.domain.Account;
import tr.edu.boun.secretary.repository.AccountRepository;
import tr.edu.boun.secretary.form.SignupForm;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(SignupForm form, BindingResult formBinding) {
        try {
            Account account = Account.builder()
                    .username(form.getUsername())
                    .password(form.getPassword())
                    .firstName(form.getFirstName())
                    .lastName(form.getLastName())
                    .build();
            accountRepository.save(account);
            return account;
        } catch (DuplicateKeyException e) {
            formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
            return null;
        }
    }
}
