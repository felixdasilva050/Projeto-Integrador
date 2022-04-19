package com.wiprobootcamp.classeA.ProjetoFinal.view;
//import model.account.CurrentAccount;
//import model.account.SpecialAccount;
//import model.creditCard.CreditCard;
//import model.customer.Customer;
//import model.customer.Individual;
//import model.customer.LegalEntity;
//import model.enumeration.AccountType;
//import model.enumeration.CustomerType;
//
//import java.util.Scanner;

public class ManageAccount {
//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        CreditCard creditCard = new CreditCard();
//        Individual individual = new Individual();
//        CurrentAccount currentAccount = new CurrentAccount();
//        LegalEntity legal = new LegalEntity();
//        CreditCard creditCard2 = new CreditCard();
//        SpecialAccount specialAccount = new SpecialAccount();
//
//        String accountSelection;
//        int selectOption;
//
//        legal.setIdLegalEntity(1);
//        legal.setCnpj("11111111/0001-00");
//        legal.setCompanyName("Wipro Bank");
//        legal.setCostumerType(CustomerType.LEGAL_ENTITY);
//
//        creditCard2.setIdCreditCard(2);
//        creditCard2.setCardLimit(1000.00);
//        creditCard2.setCardNumber("1111-1111-1111-1111");
//
//        specialAccount.setIdSpecialAccount(1);
//        specialAccount.setLimitAmount(1000.00);
//        specialAccount.setAccountType(AccountType.ESPECIAL_ACCOUNT);
//        specialAccount.setBalance(10.00);
//        specialAccount.setCustomer(legal);
//        specialAccount.setAccountNumber("1111");
//        specialAccount.setCreditCard(creditCard2);
//
//
//        individual.setIdCustomerIndiv(1);
//        individual.setCustomerName("Test");
//        individual.setCpf("999999999");
//        individual.setCostumerType(CustomerType.INDIVIDUAL);
//
//        creditCard.setIdCreditCard(1);
//        creditCard.setCardLimit(100.00);
//        creditCard.setCardNumber("9999-9999-9999-9999");
//
//        currentAccount.setIdCurrentAccout(1);
//        currentAccount.setCustomer(individual);
//        currentAccount.setAccountNumber("9999");
//        currentAccount.setAccountType(AccountType.CURRENT_ACCOUNT);
//        currentAccount.setBalance(1000.000);
//        currentAccount.setCreditCard(creditCard);
//
//        do {
//
//            System.out.println("Digite o número da conta: ");
//            accountSelection = scanner.next();
//            if (accountSelection.equals("9999")) {
//
//                do {
//                    System.out.println("Digite uma opção :\n" +
//                            "1 (Para sacar)\n" +
//                            "2 (Para depositar)\n" +
//                            "3 (Para extrato)\n" +
//                            "4 (Informações da sua conta)\n" +
//                            "0 (Encerrar)");
//
//                    selectOption = scanner.nextInt();
//
//                    switch (selectOption) {
//                        case 1:
//                            System.out.println("Digite o valor para saque: ");
//                            double withdrawCash = scanner.nextDouble();
//                            currentAccount.withdrawCash(withdrawCash);
//                            currentAccount.checkBalance(currentAccount.getAccountNumber());
//                            break;
//                        case 2:
//                            System.out.println("Digite o valor do depósito: ");
//                            double depositAmount = scanner.nextDouble();
//                            currentAccount.cashDeposit(depositAmount);
//                            break;
//                        case 3:
//                            currentAccount.checkBalance(currentAccount.getAccountNumber());
//                            break;
//                        case 4:
//                            currentAccount.obtainAccountData(currentAccount.getAccountNumber());
//                            break;
//                        case 5:
//                            System.out.println("Fim");
//                            break;
//                        default:
//                            System.out.println("Opção não encontrada. Tente novamente!");
//                    }
//                } while (selectOption != 0);
//            } else if (accountSelection.equals("1111")) {
//                do {
//
//                    System.out.println("Digite uma opção :\n" +
//                            "1 (Para sacar)\n" +
//                            "2 (Para depositar)\n" +
//                            "3 (Para extrato)\n" +
//                            "4 (Informações da sua conta)\n" +
//                            "5 (Saque Especial)\n" +
//                            "0 (Encerrar)");
//
//                    selectOption = scanner.nextInt();
//
//                    switch (selectOption) {
//                        case 1:
//                            System.out.println("Digite o valor para saque: ");
//                            double withdrawCash = scanner.nextDouble();
//                            specialAccount.withdrawCash(withdrawCash);
//                            specialAccount.checkBalance(specialAccount.getAccountNumber());
//                            break;
//                        case 2:
//                            System.out.println("Digite o valor do depósito: ");
//                            double depositAmount = scanner.nextDouble();
//                            specialAccount.cashDeposit(depositAmount);
//                            break;
//                        case 3:
//                            specialAccount.checkBalance(specialAccount.getAccountNumber());
//                            break;
//                        case 4:
//                            specialAccount.obtainAccountData(specialAccount.getAccountNumber());
//                            System.out.println("Você ainda tem R$" + specialAccount.getLimitAmount() + " de limite especial.");
//                            break;
//                        case 5:
//                            if (specialAccount.getAccountType() == AccountType.ESPECIAL_ACCOUNT) {
//                                System.out.println("Digite o valor para saque: ");
//                                double specialWithdrawValue = scanner.nextDouble();
//                                specialAccount.specialWithdraw(specialWithdrawValue);
//                                specialAccount.checkBalance(specialAccount.getAccountNumber());
//                                System.out.println("Você ainda tem R$" + specialAccount.getLimitAmount() + " de limite especial.");
//                            }
//                            break;
//                        case 0:
//                            System.out.println("Fim");
//                            break;
//                        default:
//                            System.out.println("Opção não encontrada. Tente novamente!");
//                    }
//                } while (selectOption != 0);
//
//            } else {
//                System.out.println("Conta inexistente!");
//            }
//        }while (!accountSelection.equals("0"));
//    }
}
