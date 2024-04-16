package com.sptech.bankingapp.service;

import com.sptech.bankingapp.dto.AccountDTO;
import com.sptech.bankingapp.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO getAccountByID(Long id);

    AccountDTO deposit(Long id, double amount);

    AccountDTO withdraw(Long id, double amount);

    List<AccountDTO> getAllAccounts();

    void deleteAccount(Long id);

}
