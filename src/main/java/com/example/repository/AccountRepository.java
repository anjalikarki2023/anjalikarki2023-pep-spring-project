package com.example.repository;

import java.util.Optional;
import com.example.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    Optional <Account> getAccountByUsernameAndPassword(String username, String password);
    Optional<Account> findByUsername(String username);
    
    
    
}
