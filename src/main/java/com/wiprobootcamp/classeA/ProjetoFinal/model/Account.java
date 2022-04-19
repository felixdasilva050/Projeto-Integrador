package com.wiprobootcamp.classeA.ProjetoFinal.model;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;
import com.wiprobootcamp.classeA.ProjetoFinal.enums.CustomerType;

public abstract class Account {
    public String accountNumber;
    public Double balance;
    public CreditCard creditCard;
    public AccountType accountType;
    public CustomerType customerType;
    public Customer customer;


    public Customer getCustomer() {return customer;}

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public CustomerType getCostumerType() {return customerType;}


    //Método utilizado para trânsferir
    public Boolean transferMoney(Account destinationAccount, double money) {
        if(this.getBalance() >= money){
          this.balance = this.balance - money;
          destinationAccount.balance = destinationAccount.balance + money;
          return true;
        }else{
            System.out.println("Saldo insuficiente!");
                return false;
        }
    }

    //Método utilizado para consultar saldo
    public Double checkBalance(String accountNumber) {
        if(accountNumber.isEmpty()){
            System.out.println("Conta não existe!");
        } else
            System.out.println("Seu saldo atual é de R$ " + getBalance());
            return balance;
    }

    //Método utilizado para sacar dinheiro
    public Boolean withdrawCash(double money) {
        if(this.balance >= money) {
            this.balance = getBalance() - money;
            return true;
        } else {
            System.out.println("Saldo insuficiente");
            return false;
        }

    }

    //Método utilizado para depositar dinheiro
    public void cashDeposit(double money) {
        this.balance = getBalance() + money;
        System.out.println("Seu saldo é de R$ " + getBalance());
    }

    //Método utilizado para consultar dados da conta
    public void obtainAccountData(String accountNumber) {
        System.out.println("Número da conta: " + getAccountNumber());
        System.out.println("Saldo da conta: " + getBalance());
        System.out.println("Cartão de crédito da conta: " + getCreditCard().getCardNumber());
        if(this.getAccountType().equals(AccountType.CURRENT_ACCOUNT)){
            System.out.println("Tipo da conta: CONTA CORRENTE" );
        }else{
            System.out.println("Tipo da conta: CONTA ESPECIAL");
        }
        if(this.customer.getCostumerType().equals(CustomerType.INDIVIDUAL)){
            System.out.println("Tipo de Cliente: Pessoa Física");
        }else{
            System.out.println("Tipo de Cliente: Pessoa Jurídica");
        }

    }

}
