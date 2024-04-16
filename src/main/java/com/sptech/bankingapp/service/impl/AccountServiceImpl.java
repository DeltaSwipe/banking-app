package com.sptech.bankingapp.service.impl;

import com.sptech.bankingapp.dto.AccountDTO;
import com.sptech.bankingapp.entity.Account;
import com.sptech.bankingapp.mapper.AccountMapper;
import com.sptech.bankingapp.repository.AccountRepository;
import com.sptech.bankingapp.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.mapToAccount(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountByID(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Doesn't Exist"));
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public AccountDTO deposit(Long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Doesn't Exist"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account newBalance = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(newBalance);
    }

    @Override
    public AccountDTO withdraw(Long id, double amount) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Doesn't Exist"));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient Balance!");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account newBalance = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(newBalance);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List <Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDTO(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account doesn't exist!"));
        accountRepository.deleteById(id);
    }


}
