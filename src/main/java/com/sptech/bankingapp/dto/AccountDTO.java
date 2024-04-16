package com.sptech.bankingapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class AccountDTO {

    private long id;
    private String accountHolderName;
    private double balance;
}
