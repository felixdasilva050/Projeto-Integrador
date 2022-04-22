package com.wiprobootcamp.classeA.ProjetoFinal.enums;

import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;

public enum CustomerType {
    LEGAL_ENTITY(new SpecialAccount()),
    LEGAL_ENTITY2(new CurrentAccount()),
    INDIVIDUAL(new CurrentAccount()),
    INDIVIDUAL2(new SpecialAccount());

    CustomerType(CurrentAccount currentAccount) {}

    CustomerType(SpecialAccount specialAccount){}
}
