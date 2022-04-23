package com.wiprobootcamp.classeA.ProjetoFinal.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.wiprobootcamp.classeA.ProjetoFinal.enums.AccountType;
import com.wiprobootcamp.classeA.ProjetoFinal.model.TransactionsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wiprobootcamp.classeA.ProjetoFinal.model.CurrentAccount;
import com.wiprobootcamp.classeA.ProjetoFinal.repository.CurrentAccountRepository;

@Service
public class CurrentAccountService {

	Logger logger = Logger.getLogger(CurrentAccountService.class.getName());

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
	public CurrentAccount update(CurrentAccount currentAccount) throws Exception {

		Optional<CurrentAccount> findCurrentAccountInDb = currentAccountRepository.findByAccountNumber(currentAccount.getAccountNumber());
		if(findCurrentAccountInDb.isEmpty()){
			logger.info("Conta não localizada no Banco de Dados");
			throw new Exception("Conta não localizada!");
		}
		CurrentAccount newCurrentAccount = findById(currentAccount.getIdAccount());
		newCurrentAccount.setAccountNumber(currentAccount.getAccountNumber());
		newCurrentAccount.setAccountType(currentAccount.getAccountType());
		newCurrentAccount.setBalance(currentAccount.getBalance());
		newCurrentAccount.setIndividual(currentAccount.getIndividual());
		
		return currentAccountRepository.save(newCurrentAccount);
	}
	
	//Método que cria uma conta corrente
	public CurrentAccount create(CurrentAccount currentAccount) throws Exception {
		Optional<CurrentAccount> findCurrentAccountInDb = currentAccountRepository.findByAccountNumber(currentAccount.getAccountNumber());

			if(findCurrentAccountInDb.isPresent()) {
				logger.info("Conta já cadastrada no Banco de dados!");
				throw new Exception("Conta corrente já cadastrada!");
			}

			CurrentAccount newCurrentAccount = new CurrentAccount();
			newCurrentAccount.setAccountNumber(currentAccount.getAccountNumber());
			newCurrentAccount.setCreditCard(currentAccount.getCreditCard());
			newCurrentAccount.setAccountType(AccountType.CURRENT_ACCOUNT);
			newCurrentAccount.setBalance(currentAccount.getBalance());
			newCurrentAccount.setIndividual(currentAccount.getIndividual());
			return currentAccountRepository.save(newCurrentAccount);
	}
	
	//Método que deleta uma conta corrente dado um ID
	public void delete(Integer id) {
		findById(id);
		currentAccountRepository.deleteById(id);
	}

	public void withdrawCash(TransactionsRequest transactionsRequest) throws Exception {

		verifyWithdraw(transactionsRequest);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String todayFormated = sdf.format(new Date());

		transactionsRequest.setTransactionDate(todayFormated);

	}

	public void depositMoney(TransactionsRequest transactionsRequest) throws Exception {
		Optional<CurrentAccount> findAccountInDb = currentAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());
		if(findAccountInDb.isEmpty()) {
			logger.info("Conta não existe no Banco de Dados");
			throw new Exception("Conta não localizada!");
		}
		Double defineBalance = findAccountInDb.get().getBalance() + transactionsRequest.getDebitValue();
		findAccountInDb.get().setBalance(defineBalance);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
		String todayFormated = sdf.format(new Date());

		transactionsRequest.setTransactionDate(todayFormated);

		currentAccountRepository.save(findAccountInDb.get());
	}

	private void verifyWithdraw(TransactionsRequest transactionsRequest) throws Exception {
		Optional<CurrentAccount> findAccountInDb = currentAccountRepository.findByAccountNumber(transactionsRequest.getAccountNumber());

		//verifica se a conta existe no DB, caso não retorna exception
		if(findAccountInDb.isEmpty()) {
			logger.info("Conta não existe no Banco de Dados");
			throw new Exception("Conta não localizada!");

			//verifica se possui saldo em conta para sacar, caso possuia apenas realiza o saque
		} else if (transactionsRequest.getDebitValue() <= findAccountInDb.get().getBalance()) {
			findAccountInDb.get().setBalance(findAccountInDb.get().getBalance() - transactionsRequest.getDebitValue());
			currentAccountRepository.save(findAccountInDb.get());

			//Se não a aplicação apenas devolde a mensagem de saldo insuficiente
		} else {
			logger.info("Saldo insuficiente!");
		}
	}

}
