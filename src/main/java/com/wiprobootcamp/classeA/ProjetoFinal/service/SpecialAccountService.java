package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.SpecialAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SpecialAccountService {

	Logger logger = Logger.getLogger(SpecialAccountService.class.getName());

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
	public SpecialAccount update(SpecialAccount specialAccount) throws Exception {

		Optional<SpecialAccount> findSpecialAccountInDb = specialAccountRepository.
				findByAccountNumber(specialAccount.getAccountNumber());
		if(findSpecialAccountInDb.isEmpty()){
			logger.info("Conta não localizada no Banco de Dados");
			throw new Exception("Conta não localizada!");
		}
		SpecialAccount newSpecialAccount = findById(specialAccount.getIdAccount());
		newSpecialAccount.setAccountNumber(specialAccount.getAccountNumber());
		newSpecialAccount.setAccountType(specialAccount.getAccountType());
		newSpecialAccount.setBalance(specialAccount.getBalance());
		newSpecialAccount.setCreditCard(specialAccount.getCreditCard());
		newSpecialAccount.setLimitAmount(specialAccount.getLimitAmount());
		
		return specialAccountRepository.save(newSpecialAccount);
	}
	
	//Método que cria uma conta corrente
	public SpecialAccount create(SpecialAccount specialAccount) throws Exception {
		Optional<SpecialAccount> findSpecialAccountInDb = specialAccountRepository.
				findByAccountNumber(specialAccount.getAccountNumber());

			if(findSpecialAccountInDb.isPresent()) {
				logger.info("Conta já cadastrada no Banco de dados!");
				throw new Exception("Conta especial já cadastrada!");
			}

			SpecialAccount newSpecialAccount = new SpecialAccount();
			newSpecialAccount.setAccountNumber(specialAccount.getAccountNumber());
			newSpecialAccount.setCreditCard(specialAccount.getCreditCard());
			newSpecialAccount.setAccountType(AccountType.ESPECIAL_ACCOUNT);
			newSpecialAccount.setLimitAmount(specialAccount.getLimitAmount());
			newSpecialAccount.setBalance(specialAccount.getBalance());
			return specialAccountRepository.save(newSpecialAccount);
	}

	//Método que deleta uma conta corrente informando o número da conta
	public void delete(Integer id) throws Exception {
		Optional<SpecialAccount> findSpecialAccount = specialAccountRepository.
				findById(id);
		if(findSpecialAccount.isEmpty()) {
			logger.info("Conta não existe no banco de dados!");
			throw new Exception("Conta inexistente!");
		}
		specialAccountRepository.deleteById(findSpecialAccount.get().getIdAccount());
	}

}
