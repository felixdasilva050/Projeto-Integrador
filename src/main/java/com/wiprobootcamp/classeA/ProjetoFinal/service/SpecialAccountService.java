package com.wiprobootcamp.classeA.ProjetoFinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.SpecialAccountRepository;

@Service
public class SpecialAccountService {

	@Autowired
	private SpecialAccountRepository specialAccountRepository;
	
	//método que busca uma conta corrente pelo seu ID
	public SpecialAccount findById(Integer id) {
		Optional<SpecialAccount> object = specialAccountRepository.findById(id);
		return object.orElse(null);
	}
	
	//Método que lista todas as contas correntes
	public List<SpecialAccount> findAll(){
		return specialAccountRepository.findAll();
	}
	
	//Método que atualiza uma conta corrente dado um ID e uma conta corrente
	public SpecialAccount update(Integer id, SpecialAccount specialAccount) {
		SpecialAccount newSpecialAccount = findById(id);
		
		newSpecialAccount.setAccountNumber(specialAccount.getAccountNumber());
		newSpecialAccount.setAccountType(specialAccount.getAccountType());
		newSpecialAccount.setBalance(specialAccount.getBalance());
		newSpecialAccount.setCreditCard(specialAccount.getCreditCard());
		//newSpecialAccount.setCustomer(specialAccount.getCustomer());
		newSpecialAccount.setLimitAmount(specialAccount.getLimitAmount());
		
		return specialAccountRepository.save(newSpecialAccount);
	}
	
	//Método que cria uma conta corrente
	public SpecialAccount create(SpecialAccount specialAccount) {
		return specialAccountRepository.save(specialAccount);
	}
	
	//Método que deleta uma conta corrente dado um ID
	public void delete(Integer id) {
		findById(id);
		specialAccountRepository.deleteById(id);
	}
}
