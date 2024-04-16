package com.sptech.bankingapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column( name = "account_holder_name")
    private String accountHolderName;
    private double balance;
}
