package com.wiprobootcamp.classeA.ProjetoFinal.enums;

import com.wiprobootcamp.classeA.ProjetoFinal.model.Account;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;

public enum AccountType {
    CURRENT_ACCOUNT(new CurrentAccount()),
    ESPECIAL_ACCOUNT(new SpecialAccount());

    AccountType(CurrentAccount currentAccount) {}

    AccountType(SpecialAccount specialAccount){}
}
