package com.sptech.bankingapp.controller;

import com.sptech.bankingapp.dto.AccountDTO;
import com.sptech.bankingapp.entity.Account;
import com.sptech.bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO){
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id){
        AccountDTO accountDTO = accountService.getAccountByID(id);
        return ResponseEntity.ok(accountDTO);
    }

    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDTO> deposit(@PathVariable Long id,@RequestBody Map<String, Double> amt){
        Double amount = amt.get("addMoney");
        AccountDTO accountDTO = accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDTO);

    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable Long id,@RequestBody Map<String,Double> amt){
        Double amount = amt.get("withdrawMoney");
        AccountDTO accountDTO = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        List<AccountDTO> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
            accountService.deleteAccount(id);
            return ResponseEntity.ok("Account is deleted Successfully!");
    }
}
