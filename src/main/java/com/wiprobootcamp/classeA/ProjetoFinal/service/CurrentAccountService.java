package com.wiprobootcamp.classeA.ProjetoFinal.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccountRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Customer;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CurrentAccountRepository;

@Service
public class CurrentAccountService {

	Logger logger = Logger.getLogger(CurrentAccountService.class.getName());

	private final CurrentAccountRepository currentAccountRepository;

	private final CustomerRepository customerRepository;


	public CurrentAccountService(CurrentAccountRepository currentAccountRepository, CustomerRepository customerRepository) {
		this.currentAccountRepository = currentAccountRepository;
		this.customerRepository = customerRepository;
	}


//
//	//método que busca uma conta corrente pelo seu ID
//	public CurrentAccount findById(Integer id) {
//		Optional<CurrentAccount> object = currentAccountRepository.findById(id);
//		return object.orElse(null);
//	}
//
	//Método que lista todas as contas correntes
	public List<CurrentAccount> findAll(){
		return currentAccountRepository.findAll();
	}
//
	//Método que atualiza uma conta corrente dado um ID e uma conta corrente
	public CurrentAccount updateCurrentAccount(CurrentAccount currentAccount) throws Exception {

		Optional<CurrentAccount> findCurrentAccountInDb = currentAccountRepository.findByAccountNumber(currentAccount.getAccountNumber());
		if(findCurrentAccountInDb.isEmpty()){
			logger.info("Conta não localizada no Banco de Dados");
			throw new Exception("Conta não localizada!");
		}
		CurrentAccount newCurrentAccount = new CurrentAccount();
		newCurrentAccount.setIdAccount(currentAccount.getIdAccount());
		newCurrentAccount.setAccountNumber(currentAccount.getAccountNumber());
		newCurrentAccount.setAccountType(AccountType.CURRENT_ACCOUNT);
		newCurrentAccount.setBalance(currentAccount.getBalance());
		newCurrentAccount.setCustomer(findCurrentAccountInDb.get().getCustomer());

		return currentAccountRepository.save(newCurrentAccount);
	}

	//Método que cria uma conta corrente
	public CurrentAccount createCurrentAccount(CurrentAccountRequest currentAccountRequest) throws Exception {

		Optional<CurrentAccount> findCurrentAccountInDb = currentAccountRepository.findByAccountNumber(currentAccountRequest.getAccountNumber());
			if(findCurrentAccountInDb.isPresent()) {
				logger.info("Conta já cadastrada no Banco de dados!");
				throw new Exception("Conta corrente já cadastrada!");
			}
		Optional<Customer> findCustomerInDb = customerRepository.findByDocumentNumber(currentAccountRequest.getDocumentNumber());
			if (findCustomerInDb.isEmpty()) {
				throw new  Exception("Cliente não existe no banco de dados");
			}

			CurrentAccount newCurrentAccount = new CurrentAccount();
			newCurrentAccount.setAccountNumber(currentAccountRequest.getAccountNumber());
			newCurrentAccount.setCreditCard(currentAccountRequest.getCreditCard());
			newCurrentAccount.setAccountType(AccountType.CURRENT_ACCOUNT);
			newCurrentAccount.setBalance(currentAccountRequest.getBalance());
			newCurrentAccount.setCustomer(findCustomerInDb.get());


			return currentAccountRepository.save(newCurrentAccount);
	}
//
//	//Método que deleta uma conta corrente informando o número da conta
//	public void delete(Integer id) throws Exception {
//		Optional<CurrentAccount> findCurrentAccount = currentAccountRepository.findById(id);
//		if(findCurrentAccount.isEmpty()) {
//			logger.info("Conta não existe no banco de dados!");
//			throw new Exception("Conta inexistente!");
//		}
//		currentAccountRepository.deleteById(findCurrentAccount.get().getIdAccount());
//	}

}
