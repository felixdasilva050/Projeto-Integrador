package com.wiprobootcamp.classeA.ProjetoFinal.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.wiprobootcamp.classeA.ProjetoFinal.CustomException.BusinessException;
import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;
import com.wiprobootcamp.classeA.ProjetoFinal.request.CurrentAccountRequest;
import com.wiprobootcamp.classeA.ProjetoFinal.model.Customer;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CustomerRepository;
import com.wiprobootcamp.classeA.ProjetoFinal.request.TransactionsRequest;
import org.springframework.stereotype.Service;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CurrentAccountRepository;

@Service
public class CurrentAccountService {

	Logger logger = Logger.getLogger(CurrentAccountService.class.getName());

	private final CurrentAccountRepository currentAccountRepository;
	private final CustomerRepository customerRepository;
	private final ReceiptService receiptService;


	public CurrentAccountService(CurrentAccountRepository currentAccountRepository, CustomerRepository customerRepository, ReceiptService receiptService) {
		this.currentAccountRepository = currentAccountRepository;
		this.customerRepository = customerRepository;
		this.receiptService = receiptService;
	}

	//método que busca uma conta corrente pelo seu ID
	public CurrentAccount findCurrentAccountById(Integer id) {
		Optional<CurrentAccount> object = currentAccountRepository.findById(id);
		return object.orElse(null);
	}

	//Método que lista todas as contas correntes
	public List<CurrentAccount> findAllCurrentAccount(){
		return currentAccountRepository.findAll();
	}

	//Método que atualiza uma conta corrente dado um ID e uma conta corrente
	public CurrentAccount updateCurrentAccount(CurrentAccount currentAccount) throws BusinessException {
		Optional<CurrentAccount> findCurrentAccountInDb = currentAccountRepository.findByAccountNumber(currentAccount.getAccountNumber());
		if(findCurrentAccountInDb.isEmpty()){
			logger.info("Conta não localizada no Banco de Dados");
			throw new BusinessException("Conta não localizada!");
		}
		CurrentAccount upCurrentAccount = new CurrentAccount();
		upCurrentAccount.setIdAccount(currentAccount.getIdAccount());
		upCurrentAccount.setAccountNumber(currentAccount.getAccountNumber());
		upCurrentAccount.setAccountType(AccountType.CURRENT_ACCOUNT);
		upCurrentAccount.setBalance(currentAccount.getBalance());
		upCurrentAccount.setCustomer(findCurrentAccountInDb.get().getCustomer());
		return currentAccountRepository.save(upCurrentAccount);
	}

	//Método que cria uma conta corrente
	public CurrentAccount createCurrentAccount(CurrentAccountRequest currentAccountRequest) throws BusinessException {
		Optional<CurrentAccount> findCurrentAccountInDb = currentAccountRepository.findByAccountNumber(currentAccountRequest.getAccountNumber());
			if(findCurrentAccountInDb.isPresent()) {
				logger.info("Conta já cadastrada no Banco de dados!");
				throw new BusinessException("Conta corrente já cadastrada!");
			}
		Optional<Customer> findCustomerInDb = customerRepository.findByDocumentNumber(currentAccountRequest.getDocumentNumber());
			if (findCustomerInDb.isEmpty()) {
				throw new  BusinessException("Cliente não existe no banco de dados");
			}
			CurrentAccount newCurrentAccount = new CurrentAccount();
			newCurrentAccount.setAccountNumber(currentAccountRequest.getAccountNumber());
			newCurrentAccount.setAccountType(AccountType.CURRENT_ACCOUNT);
			newCurrentAccount.setBalance(currentAccountRequest.getBalance());
			newCurrentAccount.setCustomer(findCustomerInDb.get());
			return currentAccountRepository.save(newCurrentAccount);
	}

	//Método que deleta uma conta corrente informando o número da conta
	public void delete(Integer id) throws BusinessException {
		Optional<CurrentAccount> findCurrentAccount = currentAccountRepository.findById(id);
		if(findCurrentAccount.isEmpty()) {
			logger.info("Conta não existe no banco de dados!");
			throw new BusinessException("Conta inexistente!");
		}
		currentAccountRepository.deleteById(findCurrentAccount.get().getIdAccount());
	}
	public String withdrawCash(TransactionsRequest transactionsRequest) throws Exception {

		verifyWithdraw(transactionsRequest);

		return receiptService.createReceipt(transactionsRequest);
	}


	public String depositMoney(TransactionsRequest transactionsRequest) throws BusinessException {
		Optional<CurrentAccount> findAccountInDb = currentAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());
		if(findAccountInDb.isEmpty()) {
			logger.info("Conta não existe no Banco de Dados");
			throw new BusinessException("Conta não localizada!");
		}
		Double defineBalance = findAccountInDb.get().getBalance() + transactionsRequest.getValue();
		findAccountInDb.get().setBalance(defineBalance);

		currentAccountRepository.save(findAccountInDb.get());
		return receiptService.createReceipt(transactionsRequest);
	}

	private void verifyWithdraw(TransactionsRequest transactionsRequest) throws BusinessException {
		Optional<CurrentAccount> findAccount = currentAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());
		if(findAccount.isEmpty()) {
			logger.info("Conta não existe no Banco de Dados");
			throw new BusinessException("Conta não localizada!");
			//verifica se possui saldo em conta para sacar, caso possuia apenas realiza o saque
		}else if (transactionsRequest.getValue() <= findAccount.get().getBalance()) {

			findAccount.get().setBalance(findAccount.get().getBalance() - transactionsRequest.getValue());
			currentAccountRepository.save(findAccount.get());

			//Se não a aplicação apenas devolde a mensagem de saldo insuficiente
		} else {
			throw new BusinessException("Conta não possui saldo!");
		}
	}

}
