package com.wiprobootcamp.classeA.ProjetoFinal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CurrentAccountRepository;

@Service
public class CurrentAccountService {

	@Autowired
	private CurrentAccountRepository currentAccountRepository;
	
	//método que busca uma conta corrente pelo seu ID
	public CurrentAccount findById(Integer id) {
		Optional<CurrentAccount> object = currentAccountRepository.findById(id);
		return object.orElse(null);
	}
	
	//Método que lista todas as contas correntes
	public List<CurrentAccount> findAll(){
		return currentAccountRepository.findAll();
	}
	
	//Método que atualiza uma conta corrente dado um ID e uma conta corrente
	public CurrentAccount update(Integer id, CurrentAccount currentAccount) {
		CurrentAccount newCurrentAccount = findById(id);
		
		newCurrentAccount.setAccountNumber(currentAccount.getAccountNumber());
		newCurrentAccount.setAccountType(currentAccount.getAccountType());
		newCurrentAccount.setBalance(currentAccount.getBalance());
		newCurrentAccount.setCreditCard(currentAccount.getCreditCard());
		newCurrentAccount.setIndividual(currentAccount.getIndividual());
		
		return currentAccountRepository.save(newCurrentAccount);
	}
	
	//Método que cria uma conta corrente
	public CurrentAccount create(CurrentAccount currentAccount) {
		return currentAccountRepository.save(currentAccount);
	}
	
	//Método que deleta uma conta corrente dado um ID
	public void delete(Integer id) {
		findById(id);
		currentAccountRepository.deleteById(id);
	}
	
	//Ainda falta criar os métodos de sacar, depositar, verficar saldo, obter dados da conta;
}
