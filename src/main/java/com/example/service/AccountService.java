package com.example.service;

import com.example.entity.Account;
import com.example.repository.*;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

@Service
public class AccountService {
        
@Autowired
private AccountRepository accountRepository;

    public Account NewregisterHandle(Account account) {
        if (account == null || account.getUsername() == null ||account.getUsername().isBlank()||account.getPassword().isBlank()|| account.getUsername().trim().isEmpty() || 
            account.getPassword() == null || account.getPassword().length() < 4) {
            return null; 
        }
        
        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount.isPresent()) {
                    throw new RuntimeException("already exist");
    }
        return accountRepository.save(account);
    }

    public Account UserLogin(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.isEmpty()) {
            return null; 
        }
        return accountRepository.getAccountByUsernameAndPassword(username, password).orElse(null);
        
    }
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account getAccountById(int accountId) {
       
        Optional<Account> account =accountRepository.findById(accountId);
        return account.orElse(null);
    } 
}
