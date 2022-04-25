package com.wiprobootcamp.classeA.ProjetoFinal.service;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Customer;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.model.SpecialAccountRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CustomerRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.SpecialAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SpecialAccountService {

	Logger logger = Logger.getLogger(SpecialAccountService.class.getName());

	@Autowired
	private SpecialAccountRepository specialAccountRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
//	//método que busca uma conta corrente pelo seu ID
//	public SpecialAccount findById(Integer id) {
//		Optional<SpecialAccount> object = specialAccountRepository.findById(id);
//		return object.orElse(null);
//	}
	
	//Método que lista todas as contas correntes
	public List<SpecialAccount> findAll(){
		return specialAccountRepository.findAll();
	}
	
//	Método que atualiza uma conta corrente dado um ID e uma conta corrente
	public SpecialAccount updateSpecialAccount(SpecialAccount specialAccount) throws Exception {

		Optional<SpecialAccount> findSpecialAccountInDb = specialAccountRepository.findByAccountNumber(specialAccount.getAccountNumber());
		if(findSpecialAccountInDb.isEmpty()){
			logger.info("Conta não localizada no Banco de Dados");
			throw new Exception("Conta não localizada!");
		}
		SpecialAccount newSpecialAccount = new SpecialAccount();
		newSpecialAccount.setIdAccount(findSpecialAccountInDb.get().getIdAccount());
		newSpecialAccount.setAccountNumber(specialAccount.getAccountNumber());
		newSpecialAccount.setAccountType(AccountType.ESPECIAL_ACCOUNT);
		newSpecialAccount.setBalance(specialAccount.getBalance());
		newSpecialAccount.setCreditCard(specialAccount.getCreditCard());
		newSpecialAccount.setLimitAmount(specialAccount.getLimitAmount());
		newSpecialAccount.setCustomer(findSpecialAccountInDb.get().getCustomer());

		return specialAccountRepository.save(newSpecialAccount);
	}
	
	//Método que cria uma conta corrente
	public SpecialAccount create(SpecialAccountRequest specialAccountRequest) throws Exception {
		Optional<SpecialAccount> findSpecialAccountInDb = specialAccountRepository.
				findByAccountNumber(specialAccountRequest.getAccountNumber());

			if(findSpecialAccountInDb.isPresent()) {
				logger.info("Conta já cadastrada no Banco de dados!");
				throw new Exception("Conta especial já cadastrada!");
			}

		Optional<Customer> findCustomer = customerRepository.findByDocumentNumber(specialAccountRequest.getDocumentNumber());
		if(findCustomer.isEmpty()) {
			logger.info("Cliente não localizado no banco de dados!");
			throw new Exception("Cliente não localizado!");
		}

			SpecialAccount newSpecialAccount = new SpecialAccount();
			newSpecialAccount.setAccountNumber(specialAccountRequest.getAccountNumber());
			newSpecialAccount.setCreditCard(specialAccountRequest.getCreditCard());
			newSpecialAccount.setAccountType(AccountType.ESPECIAL_ACCOUNT);
			newSpecialAccount.setLimitAmount(specialAccountRequest.getLimitAmount());
			newSpecialAccount.setBalance(specialAccountRequest.getBalance());
			newSpecialAccount.setCustomer(findCustomer.get());
			return specialAccountRepository.save(newSpecialAccount);
	}

//	//Método que deleta uma conta corrente informando o número da conta
//	public void delete(Integer id) throws Exception {
//		Optional<SpecialAccount> findSpecialAccount = specialAccountRepository.
//				findById(id);
//		if(findSpecialAccount.isEmpty()) {
//			logger.info("Conta não existe no banco de dados!");
//			throw new Exception("Conta inexistente!");
//		}
//		specialAccountRepository.deleteById(findSpecialAccount.get().getIdAccount());
//	}

}
